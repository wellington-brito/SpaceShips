package spaceships.appswb.com.spaceships.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.adapter.RecyclerAdapter;
import spaceships.appswb.com.spaceships.bo.ShipBO;
import spaceships.appswb.com.spaceships.dao.DatabaseHelper;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MyAsyncTask extends AsyncTask<String, String, String> {


    ProgressDialog progessDialo;
    Context context;
    List<Ship> spaceships = new ArrayList<>();
    ListView mListShips;
    String valorparaconverter;
    AppCompatImageButton imgBtn;
    AppCompatTextView textView;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    public MyAsyncTask(Context context, ListView mList) {
        this.context = context;
        this.mListShips = mList;
    }


    public MyAsyncTask(Home home, RecyclerView recyclerView, RecyclerAdapter recyclerAdapter, AppCompatImageButton imgBtn, AppCompatTextView textView) {
        this.context = home;
        this.recyclerView = recyclerView;
        this.recyclerAdapter = recyclerAdapter;
        this.imgBtn = imgBtn;
        this.textView = textView;
    }


    //Responsavel por carregar o Objeto JSON
    public static String getJSONFromAPI(String url) {
        String retorno = "";
        try {
            URL apiEnd = new URL(url);
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
            } else {
                is = conexao.getErrorStream();
            }

            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @NonNull
    private static String converterInputStreamToString(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while ((linha = br.readLine()) != null) {
                buffer.append(linha);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPreExecute() {
        this.progessDialo = ProgressDialog.show(context, context.getString(R.string.TituloEspera), context.getString(R.string.MensagemEspera), false, false);
    }

    //Chama o método para conectar com a API
    @Override
    protected String doInBackground(String... strings) {
        try {
            return this.getJSONFromAPI("https://swapi.co/api/starships/");
        } catch (Exception e) {
            String erro = e.getMessage();
            return erro;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
                JSONObject mJsonObject = new JSONObject(result);
                JSONArray json = mJsonObject.getJSONArray("results");
                for (int i = 0; i < json.length(); i++) {
                    mJsonObject = (JSONObject) json.get(i);
                    Ship spaceship = new Ship();
                    spaceship.setName(mJsonObject.getString("name"));
                    spaceship.setModel(mJsonObject.getString("model"));
                    spaceship.setCost_in_credits(mJsonObject.getString("cost_in_credits"));
                    spaceship.setLength(mJsonObject.getString("length"));
                    spaceship.setCrew(mJsonObject.getString("crew"));
                    spaceship.setPassengers(mJsonObject.getString("passengers"));
                    spaceship.setCargo_capacity(mJsonObject.getString("cargo_capacity"));
                    spaceship.setConsumables(mJsonObject.getString("consumables"));
                    this.spaceships.add(spaceship);
                }

                if (verifyDatabse() == true) {
                    this.salvarDados();
                    this.exibirDados();
                } else {
                    this.exibirDados();
                }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.progessDialo.hide();
    }

    private boolean verifyDatabse() {
        DatabaseHelper dh = new DatabaseHelper(this.context);
        ShipBO shipBO = new ShipBO();
        List<Ship> lista = new ArrayList<>();
        try {
            lista = shipBO.searchShips(this.context);
        } catch (SQLException e) {
        }
        if (lista.size() == 0)
            return true;
        else
            return false;
    }

    @SuppressLint("ResourceType")
    private void salvarDados() throws SQLException {
        ShipBO shipBO = new ShipBO();
        Ship ship = new Ship();
        for (int i = 0; i < this.spaceships.size(); i++) {
            ship = this.spaceships.get(i);
            shipBO.salvar(ship, this.context);
        }
    }

    @SuppressLint("ResourceType")
    public void exibirDados() throws Exception {
        // Associacao do recyclerAdapter ao recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerAdapter = new RecyclerAdapter(spaceships);
        recyclerView.setAdapter(recyclerAdapter);

      clickItem();
    }

    private void clickItem() {

        ItemClickSupport.addTo(this.recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(context, Details.class);
                        intent.putExtra("ship", (Parcelable) spaceships.get(position));
                        context.startActivity(intent);
                    }
                });
    }

}


package spaceships.appswb.com.spaceships.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.adapter.ShipAdapter;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MyAsyncTask extends AsyncTask<String, String, String> {

    //Declarando variaveis.
    ProgressDialog progessDialo;
    Context context;
    List<Ship> ships = new ArrayList<>();
    ListView mListShips;
    Float resultado;
    String criptocoinChoosed;

    //Construtor da classe.
    public MyAsyncTask(Context context, ListView mList) {
        this.context = context;
        this.mListShips = mList;

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

    @Override
    protected String doInBackground(String... strings) {
        try {
            //Atribui o valor de params na posição 0(neste caso o valor a ser computado) a valorparaconverter.
            //this.valorparaconverter = strings[0];

            //Chama o método para conectar com a API
            //Passa como parametro a URL da API em formato String
            return this.getJSONFromAPI(strings[0]);
        } catch (Exception e) {

            String erro = e.getMessage();

            return erro;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        JSONArray json = null;
        JSONObject mJsonObject = new JSONObject();


        try {
            JSONArray jsonArray = mJsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject explrObject = jsonArray.getJSONObject(i);
                Ship spaceship = new Ship();
                // spaceship.setId(Integer.parseInt(mJsonObject.getString("id")));
                spaceship.setName(mJsonObject.getString("name"));
                spaceship.setModel(mJsonObject.getString("model"));
                spaceship.setCost_in_credits(mJsonObject.getString("cost_in_credits"));
                spaceship.setLength(mJsonObject.getString("length"));
                spaceship.setCrew(mJsonObject.getString("crew"));
                spaceship.setPassengers(mJsonObject.getString("passengers"));
                spaceship.setCargo_capacity(mJsonObject.getString("cargo_capacity"));
                spaceship.setConsumables(mJsonObject.getString("consumables"));

                this.ships.add(spaceship);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.exibirDados();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.progessDialo.hide();
    }

    @SuppressLint("ResourceType")
    public void exibirDados() throws Exception {

        // Associacao do Adapter a ListView
        this.mListShips.setAdapter(new ShipAdapter(context, this.ships));

    }
}


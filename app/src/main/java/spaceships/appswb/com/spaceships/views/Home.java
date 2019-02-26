package spaceships.appswb.com.spaceships.views;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.adapter.RecyclerAdapter;
import spaceships.appswb.com.spaceships.bo.ShipBO;
import spaceships.appswb.com.spaceships.dao.DatabaseHelper;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    public static final int CODIGO_ACTITIVITY = 1;
    final static String lista = "lista";
    ActionBarDrawerToggle toggle;
    List<Ship> spaceships = new ArrayList<>();
    AppCompatImageButton imgBtn;
    AppCompatTextView textView;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (verifyConection() == true) {
            this.loadDataApi();
            Toast.makeText(this, "Visualizando dados direto da api!", Toast.LENGTH_SHORT).show();
        } else {
            showMessage(getString(R.string.TituloSemConexão), getString(R.string.MensagemSemConexão));
            loadShipsOfLocalStorage();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadShipsOfLocalStorage();
    }

    private boolean verifyConection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void loadDataApi() {
        recyclerView = findViewById(R.id.rvSpaceShips);
        imgBtn = findViewById(R.id.img_see);
        textView = findViewById(R.id.textViewSeee);
        MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this, recyclerView, recyclerAdapter, imgBtn, textView);
        minhaTarefaAssincrona.execute();
    }

    public void shipsLocalStorage(View view) {
        this.loadShipsOfLocalStorage();
    }

    private void loadShipsOfLocalStorage() {
        DatabaseHelper dh = new DatabaseHelper(this);
        ShipBO shipBO = new ShipBO();
        try {
            this.spaceships = shipBO.searchShips(this);
            loadRecyclerView();
            Toast.makeText(this, "Exibindo dados locais!", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
        }
    }

    public void shipsVisualized(View view) {
        DatabaseHelper dh = new DatabaseHelper(this);
        ShipBO shipBO = new ShipBO();
        List<Ship> list = new ArrayList<>();
        try {
            list = shipBO.searchShips(this);
            this.spaceships = new ArrayList<>();
            for (Ship s : list) {
                if (s.getDataAlteracao() != null)
                    this.spaceships.add(s);
            }
            this.loadRecyclerView();
            this.costTotal();
        } catch (SQLException e) {
        }
    }

    private void loadRecyclerView() {
        recyclerView = findViewById(R.id.rvSpaceShips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(spaceships);
        recyclerView.setAdapter(recyclerAdapter);
        clickItem();
    }

    private void clickItem() {
        ItemClickSupport.addTo(this.recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(Home.this, Details.class);
                        intent.putExtra("ship", (Parcelable) spaceships.get(position));
                        Home.this.startActivity(intent);
                    }
                });
    }

    private void costTotal() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal bd2;
        for (int i = 0; i < spaceships.size(); i++) {
            if (spaceships.get(i).getDataAlteracao() != null && (!(spaceships.get(i).getCost_in_credits().equals("unknown")))) {
                bd2 = new BigDecimal(spaceships.get(i).getCost_in_credits());
                valorTotal = valorTotal.add(bd2);
            }
        }
        Toast.makeText(this, "Custo total: " + valorTotal + " credits!", Toast.LENGTH_LONG).show();
    }

    private void showMessage(String titulo, String mensagem) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("OK", null).show();
    }


}

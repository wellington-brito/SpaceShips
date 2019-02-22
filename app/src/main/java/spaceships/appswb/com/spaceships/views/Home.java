package spaceships.appswb.com.spaceships.views;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.adapter.ShipAdapter;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class Home extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    List<Ship> spaceships = new ArrayList<>();

    ListView mListships = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void carregarLista() {
        //Instancia a classe assincrona para exibir dados
        this.mListships = findViewById(R.id.listViewSpaceShips);
        MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this,this.mListships);
        minhaTarefaAssincrona.execute("https://swapi.co/api/starships/");

    }


}

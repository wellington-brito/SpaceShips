package spaceships.appswb.com.spaceships.views;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.adapter.RecyclerAdapter;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    /*private static final String TAG = "summar" ;*/
    ActionBarDrawerToggle toggle;
    List<Ship> spaceships = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.carregarLista();
    }

    public void carregarLista() {


       // ListView mListships = (ListView) findViewById(R.id.listViewSpaceShips);
        //Instancia a classe assincrona para exibir dados
        recyclerView = findViewById(R.id.rvSpaceShips);
        MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this, recyclerView, recyclerAdapter);
        minhaTarefaAssincrona.execute("1");


    }


}

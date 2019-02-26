package spaceships.appswb.com.spaceships.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.bo.ShipBO;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Details extends AppCompatActivity {
    TextView textViewValorSapaceShipName;
    TextView textViewValorPrice;
    TextView textViewValorLenght;
    TextView textViewValorCrew;
    TextView textViewValorPassangers;
    TextView textViewValorCargo_capacity;
    TextView textViewValorConsumables;
    TextView textViewValorStarship_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Ship ship = (Ship) getIntent().getSerializableExtra("ship");
        getSupportActionBar().setTitle(ship.getName());

        setAtributes(ship);
        updateShip(ship);
    }

    private void setAtributes(Ship ship){
        this.textViewValorSapaceShipName = (TextView) findViewById(R.id.textViewValorSapaceShipName);
        textViewValorSapaceShipName.setText(ship.getName());

        this.textViewValorPrice = (TextView) findViewById(R.id.textViewValorPrice);
        textViewValorPrice.setText(ship.getCost_in_credits());

        this.textViewValorLenght = (TextView) findViewById(R.id.textViewValorLenght);
        textViewValorLenght.setText(ship.getLength());

        this.textViewValorCrew = (TextView) findViewById(R.id.textViewValorCrew);
        textViewValorCrew.setText(ship.getCrew());

        this.textViewValorPassangers = (TextView) findViewById(R.id.textViewValorPassangers);
        textViewValorPassangers.setText(ship.getPassengers());

        this.textViewValorCargo_capacity = (TextView) findViewById(R.id.textViewValorCargo_capacity);
        textViewValorCargo_capacity.setText(ship.getCargo_capacity());

        this.textViewValorConsumables = (TextView) findViewById(R.id.textViewValorConsumables);
        textViewValorConsumables.setText(ship.getConsumables());

        this.textViewValorStarship_class = (TextView) findViewById(R.id.textViewValorStarship_class);
        textViewValorStarship_class.setText(ship.getStarship_class());

    }

    private void updateShip(Ship ship) {
        ShipBO shipBO = new ShipBO();
        Ship shipCorrente = new Ship();
        shipCorrente.setId(ship.getId());
        shipCorrente.setName(ship.getName());
        shipCorrente.setModel(ship.getModel());
        shipCorrente.setCost_in_credits(ship.getCost_in_credits());
        shipCorrente.setLength(ship.getLength());
        shipCorrente.setCrew(ship.getCrew());
        shipCorrente.setPassengers(ship.getPassengers());
        shipCorrente.setCargo_capacity(ship.getCargo_capacity());
        shipCorrente.setConsumables(ship.getConsumables());
        shipCorrente.setStarship_class(ship.getStarship_class());
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = sdf.format(hoje);
        shipCorrente.setDataAlteracao(data);
        try {
            shipBO.atualizar(shipCorrente, this, ship);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

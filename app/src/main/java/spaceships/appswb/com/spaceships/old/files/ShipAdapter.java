package spaceships.appswb.com.spaceships.old.files;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipAdapter extends BaseAdapter {

    private Context context;
    private List<Ship> ships = new ArrayList<>();
    int a;


    public ShipAdapter(Context context, List<Ship> listShip){
        this.context= context;
        this.ships = listShip;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ship spaceship = ships.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.ship_adapter_item_list, null);

        TextView txtSpaceShipName = (TextView) layout.findViewById(R.id.textViewValorSapaceShipName);
        txtSpaceShipName.setText(spaceship.getName());

        TextView txtPrice = (TextView) layout.findViewById(R.id.textViewValorPrice);
        txtPrice.setText(spaceship.getCost_in_credits());

        return layout;
    }


}

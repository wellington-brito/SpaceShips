package spaceships.appswb.com.spaceships.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import spaceships.appswb.com.spaceships.R;
import spaceships.appswb.com.spaceships.entity.Ship;
import spaceships.appswb.com.spaceships.views.ViewHolder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private  List<Ship> list;
    public  RecyclerAdapter(List<Ship> list){
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ship_adapter_item_list,viewGroup, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Ship ship = list.get(i);
        if (ship.getDataAlteracao() != null){
            viewHolder.imgBtn.setBackgroundResource(R.drawable.baseline_visibility_black_18dp);
            viewHolder.textView.setText("Visto");
        }
        viewHolder.textViewName.setText(ship.getName());
        viewHolder.textViewPrice.setText(ship.getCost_in_credits());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

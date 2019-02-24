package spaceships.appswb.com.spaceships.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import spaceships.appswb.com.spaceships.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewName;
    public TextView textViewPrice;

    public ViewHolder(View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.textViewValorSapaceShipName);
        this.textViewPrice = itemView.findViewById(R.id.textViewValorPrice);

    }
}

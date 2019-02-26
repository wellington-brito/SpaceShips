package spaceships.appswb.com.spaceships.views;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import spaceships.appswb.com.spaceships.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewName;
    public TextView textViewPrice;
    public AppCompatImageButton imgBtn;
    public AppCompatTextView textView;

    public ViewHolder(View itemView) {
        super(itemView);
        this.textViewName = itemView.findViewById(R.id.textViewValorSapaceShipName);
        this.textViewPrice = itemView.findViewById(R.id.textViewValorPrice);
        this.imgBtn = itemView.findViewById(R.id.img_see);
        this.textView = itemView.findViewById(R.id.textViewSeee);

    }
}

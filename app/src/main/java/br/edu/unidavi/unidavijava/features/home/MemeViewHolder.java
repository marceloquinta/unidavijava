package br.edu.unidavi.unidavijava.features.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.unidavijava.R;

/**
 * Created by marceloquinta on 03/03/2018.
 */

class MemeViewHolder extends RecyclerView.ViewHolder{

    ImageView thumbnail;
    TextView labelNome;
    TextView labelUrl;

    public MemeViewHolder(View itemView) {
        super(itemView);

        thumbnail = itemView.findViewById(R.id.img_cell);
        labelNome = itemView.findViewById(R.id.label_name);
        labelUrl = itemView.findViewById(R.id.label_url);
    }
}

package br.edu.unidavi.unidavijava.features.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.model.Meme;

/**
 * Created by marceloquinta on 03/03/2018.
 */

public class MemeAdapter extends RecyclerView.Adapter<MemeViewHolder> {

    List<Meme> memeList;
    Context context;

    public MemeAdapter(List<Meme> memeList, Context context){
        this.memeList = memeList;
        this.context = context;
    }

    @Override
    public MemeViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_meme,
                        parent,
                        false);

        MemeViewHolder myViewHolder = new MemeViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MemeViewHolder holder, int position) {
        final Meme myMeme = memeList.get(position);

        holder.labelNome.setText(myMeme.getName());
        holder.labelUrl.setText(myMeme.getUrl());

        Picasso.with(context)
                .load(myMeme.getPhotoUrl())
                .into(holder.thumbnail);

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(myMeme.getUrl()));
                context.startActivity(webIntent);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return memeList.size();
    }
}

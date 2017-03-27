package com.example.joginderpal.tellit_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by himanshu on 25/3/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{

    LayoutInflater inflater;
    Context c;
    public RecyclerAdapter(Context c) {


        this.c=c;
        inflater = LayoutInflater.from(c);

    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                    Intent i=new Intent(c,chosenStory.class);
                    c.startActivity(i);
                }
            });

        }
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.story_card_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}

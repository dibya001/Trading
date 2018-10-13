package com.example.dibya.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dibya on 30-Jan-17.
 */

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.MyViewHolder> {
    private Context ctx;
    private HashMap<String, Ongoing> details;
    private List<String> keys;

    public OngoingAdapter(Context ctx, HashMap<String, Ongoing> details) {
        this.ctx = ctx;
        this.details = details;
        keys = new ArrayList<>();
        keys.addAll(details.keySet());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ongoing, parent, false);
        //layoutView.setMinimumHeight(parent.getMeasuredHeight() / 2);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ongoing ongoing = details.get(keys.get(position));
        holder.amount.setText(ongoing.getUserId()+ "");
        holder.type.setText(ongoing.getType());
        holder.switch2.setText(ongoing.getSwitch2());
        if (ongoing.getType().equals("Long"))
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ff944d"));
        else
            holder.cardView.setCardBackgroundColor(Color.parseColor("#99ffbb"));
    }

    @Override
    public int getItemCount() {
        keys.clear();
        keys.addAll(details.keySet());
        return details.size();
    }

    public void start(final int pos) {

        Intent i = new Intent(ctx, EachOngoingData.class);
        String key = keys.get(pos);
        Log.i("data",details.get(key).getUserId());
        i.putExtra("data",  details.get(key));
        i.putExtra("key",key);
        ctx.startActivity(i);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView type, switch2, protype, amount;
        LinearLayout ll;
        CardView cardView;
        MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_complete);
            type = itemView.findViewById(R.id.type1);
            protype = itemView.findViewById(R.id.protype1);
            amount = itemView.findViewById(R.id.amount1);
            switch2 = itemView.findViewById(R.id.switch21);
            ll = itemView.findViewById(R.id.click);
            ll.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            start(getAdapterPosition());
        }
    }
}

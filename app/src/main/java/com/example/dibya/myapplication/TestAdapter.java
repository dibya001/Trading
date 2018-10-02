package com.example.dibya.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dibya on 30-Jan-17.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    Context ctx;
    ArrayList<TradeData> details;

    public TestAdapter(MainActivity ctx, ArrayList<TradeData> details) {
        this.ctx = ctx;
        this.details = details;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom, parent, false);
        //layoutView.setMinimumHeight(parent.getMeasuredHeight() / 2);
        MyViewHolder rcv = new MyViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int i = details.get(position).getId();
        holder.id.setText(String.valueOf(i));
        holder.amount.setText(String.valueOf(details.get(position).getProfit()));
        holder.type.setText(details.get(position).getType());
        holder.switch2.setText(details.get(position).getSwitch2());
        holder.protype.setText(details.get(position).getProtype());
        //int img=ctx.getResources().getIdentifier("xyz"+(position),"drawable",ctx.getPackageName());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void start(final int pos) {

        Intent i = null;
        ctx.startActivity(new Intent(ctx, EachData.class));


    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id, type, switch2, protype, amount;
        LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id1);
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

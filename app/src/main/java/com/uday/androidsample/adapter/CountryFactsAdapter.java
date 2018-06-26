package com.uday.androidsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.uday.androidsample.R;
import com.uday.androidsample.model.Facts;

public class CountryFactsAdapter  extends RecyclerView.Adapter<CountryFactsAdapter.FactsViewHolder> {

    private List<Facts> facts;
    private LayoutInflater mInflater;

    public static class FactsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;



        public FactsViewHolder(View v) {
            super(v);
            movieTitle = (itemView.findViewById(R.id.tvTitle));

        }
    }

    public CountryFactsAdapter(List<Facts> facts, Context context) {
        this.facts = facts;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public CountryFactsAdapter.FactsViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new FactsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(FactsViewHolder holder, final int position) {
        holder.movieTitle.setText(facts.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return facts.size();
    }
}
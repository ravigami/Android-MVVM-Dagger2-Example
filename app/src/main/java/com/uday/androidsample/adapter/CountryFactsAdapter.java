package com.uday.androidsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uday.androidsample.R;
import com.uday.androidsample.diprovider.GlideApp;
import com.uday.androidsample.model.Facts;

import java.util.List;

public class CountryFactsAdapter  extends RecyclerView.Adapter<CountryFactsAdapter.FactsViewHolder> {

    private List<Facts> facts;
    private LayoutInflater mInflater;
    Context context;
    public static class FactsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        ImageView imageView;
        TextView description;

        public FactsViewHolder(View v) {
            super(v);
            movieTitle = (itemView.findViewById(R.id.tvTitle));
            description = (itemView.findViewById(R.id.tvDescription));
            imageView = (itemView.findViewById(R.id.imageView));
        }
    }

    public CountryFactsAdapter(List<Facts> facts, Context context) {
        this.context = context;
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
        holder.description.setText(facts.get(position).getDescription());
        GlideApp.with(context)
                .load(facts.get(position).getImageHref())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return facts.size();
    }
}
package com.leoart.sharebook.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leoart.sharebook.BusProvider;
import com.leoart.sharebook.R;
import com.leoart.sharebook.events.BookItemClickEvent;
import com.leoart.sharebook.models.BookModel;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private int layout;
    private ArrayList<BookModel> books;
    private Bus bus;


    public RecyclerViewAdapter(ArrayList<BookModel> myDataset, int layout, Bus bus) {
        this.books = myDataset;
        this.layout = layout;
        this.bus = bus;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        final ViewHolder dataObjectHolder = new ViewHolder(view);
        dataObjectHolder.card_view.setOnClickListener(v -> {
               BusProvider.getInstance().post(new BookItemClickEvent(dataObjectHolder.ivCover));
        });
        dataObjectHolder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getInstance().post(new BookItemClickEvent(dataObjectHolder.ivCover));
            }
        });
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.ivCover.setImageResource(books.get(position).getCoverResource());
        holder.tvName.setText(books.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;
        public TextView tvAuthor;
        public TextView tvAddress;
        public ImageView ivCover;
        public CardView card_view;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvBookName);
            tvAuthor = (TextView) v.findViewById(R.id.tvBookAuthor);
            tvAddress = (TextView) v.findViewById(R.id.tvAdress);
            ivCover = (ImageView) v.findViewById(R.id.ivBookCover);
            card_view = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public void addNewItems(List<BookModel> items) {
        if (items.size() == 0) {
            //allItemsLoaded = true;
            return;
        }
        books.addAll(items);
    }
}

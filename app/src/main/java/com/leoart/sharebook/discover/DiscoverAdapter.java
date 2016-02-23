package com.leoart.sharebook.discover;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leoart.sharebook.BusProvider;
import com.leoart.sharebook.R;
import com.leoart.sharebook.data.DemoDataProvider;
import com.leoart.sharebook.events.CategoryMoreClickEvent;
import com.leoart.sharebook.models.DiscoverModel;
import com.leoart.sharebook.ui.adapters.RecyclerViewAdapter;
import com.leoart.sharebook.ui.widget.MyLinearLayoutManager;
import com.squareup.otto.Bus;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    private List<DiscoverModel> discoverModels;
    private Context context;
    private Bus bus;

    public DiscoverAdapter(Context context, List<DiscoverModel> discoverModels, Bus bus) {
        this.discoverModels = discoverModels;
        this.context = context;
        this.bus = bus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_discover, parent, false);

        final ViewHolder dataObjectHolder = new ViewHolder(view);
        dataObjectHolder.rlBooksCategoryMore.setOnClickListener(v -> {

                BusProvider.getInstance().post(new CategoryMoreClickEvent(discoverModels.get(dataObjectHolder.getAdapterPosition())));

        });
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoverModel discoverModel = discoverModels.get(position);
        if (!TextUtils.isEmpty(discoverModel.getCategoryTitle())) {
            holder.tvCategoryTitle.setText(discoverModel.getCategoryTitle());
        }

        MyLinearLayoutManager mLayoutManager = new MyLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rvBooksList.setLayoutManager(mLayoutManager);
        holder.rvBooksList.setAdapter(new RecyclerViewAdapter(DemoDataProvider.createBooks(), R.layout.item_book_cardview, bus));
    }

    @Override
    public int getItemCount() {
        if (discoverModels != null)
            return discoverModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlBooksCategoryMore;
        TextView tvCategoryTitle;
        RecyclerView rvBooksList;

        public ViewHolder(View itemView) {
            super(itemView);

            rlBooksCategoryMore = (RelativeLayout) itemView.findViewById(R.id.rlBooksCategoryMore);
            tvCategoryTitle = (TextView) itemView.findViewById(R.id.tvCategoryTitle);
            rvBooksList = (RecyclerView) itemView.findViewById(R.id.rvBooksList);
        }
    }
}

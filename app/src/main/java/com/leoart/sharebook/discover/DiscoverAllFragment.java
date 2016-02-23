package com.leoart.sharebook.discover;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoart.sharebook.BusProvider;
import com.leoart.sharebook.R;
import com.leoart.sharebook.data.DemoDataProvider;
import com.leoart.sharebook.events.BookItemClickEvent;
import com.leoart.sharebook.events.CategoryMoreClickEvent;
import com.leoart.sharebook.ui.BookDetailsActivity;
import com.leoart.sharebook.ui.DiscoverCategoryActivity;
import com.squareup.otto.Subscribe;

public class DiscoverAllFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "DiscoverFragment";
    private RecyclerView mRecyclerView;
    private DiscoverAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public DiscoverAllFragment() {
        // Required empty public constructor
    }

    public static DiscoverAllFragment newInstance() {
        DiscoverAllFragment fragment = new DiscoverAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover_list, container, false);
        initUI(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    private void initUI(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvBooksList);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DiscoverAdapter(getActivity().getBaseContext(), DemoDataProvider.createDiscoverModels(), BusProvider.getInstance());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
//        swipeRefreshLayout.setRefreshing(true);
//        swipeRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(false);
//                // говорим о том, что собираемся закончить
//                Toast.makeText(getActivity().getBaseContext(), "Done refreshing", Toast.LENGTH_SHORT).show();
//            }
//        }, 3000);
    }

    @Subscribe
    public void categoryMoreClickEvent(CategoryMoreClickEvent categoryMoreClickEvent){
        Log.d(TAG, "categotyMoreClickEvent");
        Intent intent = new Intent(getActivity(), DiscoverCategoryActivity.class);
        intent.putExtra(DiscoverCategoryActivity.CATEGORY_NAME, categoryMoreClickEvent.discoverModel.getCategoryTitle());
        startActivity(intent);
    }

    @Subscribe
    public void bookItemClickedEvent(BookItemClickEvent bookItemClickEvent){
        Log.d(TAG, "bookItemClicked");
        Activity activity = getActivity();
        if(activity!=null) {
            Intent intent = new Intent(activity, BookDetailsActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                // Pass data object in the bundle and populate details activity.
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View) bookItemClickEvent.bookCover, "bookCover");
                activity.startActivity(intent, options.toBundle());
            }else {
                activity.startActivity(intent);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
}

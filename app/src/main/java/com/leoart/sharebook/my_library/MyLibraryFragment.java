package com.leoart.sharebook.my_library;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoart.sharebook.R;
import com.leoart.sharebook.data.EmulateResponseManager;
import com.leoart.sharebook.models.BookModel;
import com.leoart.sharebook.pagination.PaginationTool;
import com.leoart.sharebook.ui.adapters.RecyclerViewAdapter;
import com.leoart.sharebook.add_new_book.AddBookActivity;
import com.leoart.sharebook.data.DemoDataProvider;
import com.leoart.sharebook.models.User;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MyLibraryFragment extends Fragment {

    private static final String TAG = "MyLibraryFragment";
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyLibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyLibraryFragment newInstance(String param1, String param2) {
        MyLibraryFragment fragment = new MyLibraryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_library, container, false);
        initUI(view);

        return view;
    }

    private void initUI(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvBooksList);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(DemoDataProvider.createBooks(), R.layout.book_cardview, null);
        mRecyclerView.setAdapter(mAdapter);

        int limit = 50;
        PaginationTool<List<BookModel>> paginationTool = PaginationTool.buildPagingObservable(mRecyclerView, offset ->  EmulateResponseManager.getInstance().getEmulateResponse(offset, limit))
                .setLimit(limit)
                .build();

        Subscription paginationSubscription = paginationTool
                .getPagingObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BookModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<BookModel> items) {
                        Log.d(TAG, "onNext!!!");
                        mAdapter.addNewItems(items);
                        mAdapter.notifyItemInserted(mAdapter.getItemCount() - items.size());
                    }
                });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_book);
        fab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getBaseContext(), AddBookActivity.class);
//                startActivity(intent,
//                        ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

}

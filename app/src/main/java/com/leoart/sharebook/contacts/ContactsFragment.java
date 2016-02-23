package com.leoart.sharebook.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoart.sharebook.R;
import com.leoart.sharebook.data.DemoDataProvider;

public class ContactsFragment  extends Fragment {

    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvContacts);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ContactsAdapter(R.layout.item_contact, DemoDataProvider.createContacts());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
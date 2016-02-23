package com.leoart.sharebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;

import com.leoart.sharebook.R;
import com.leoart.sharebook.data.DemoDataProvider;
import com.leoart.sharebook.ui.adapters.RecyclerViewAdapter;

public class DiscoverCategoryActivity extends AppCompatActivity{

    public static final String CATEGORY_NAME = "category_name";

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_discover_list);
        initUI();

        Intent intent = getIntent();
        if(intent!=null){
            String title = intent.getStringExtra(CATEGORY_NAME);
            if(!TextUtils.isEmpty(title)){
                getSupportActionBar().setTitle(title);
            }
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI(){
        mRecyclerView = (RecyclerView) findViewById(R.id.rvBooksList);
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(DemoDataProvider.createBooks(), R.layout.book_cardview, null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

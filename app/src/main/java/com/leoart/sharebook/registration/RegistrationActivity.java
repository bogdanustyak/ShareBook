package com.leoart.sharebook.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.leoart.sharebook.R;
import com.leoart.sharebook.ui.MainActivity;
import com.leoart.sharebook.utils.FragmentHelper;

public class RegistrationActivity extends AppCompatActivity implements RegistrationFirstStepFragment.OnRegistrationFirstStepInteractionListener, RegistrationSecondStepFragment.OnRegSecondStepInteractionListener{

    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showFirstRegistrationStep();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFirstRegistrationSuccess() {
        Log.d(TAG, "onFirstRegistrationSuccess");
        showSecondRegistrationStep();
    }

    @Override
    public void onRegistrationDone() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void showFirstRegistrationStep(){
        FragmentHelper.getInstance().showFragment(getSupportFragmentManager(), RegistrationFirstStepFragment.newInstance("", ""), R.id.container_registration);
    }

    private void showSecondRegistrationStep() {
        FragmentHelper.getInstance().showFragment(getSupportFragmentManager(), RegistrationSecondStepFragment.newInstance("", ""), R.id.container_registration);
    }
}

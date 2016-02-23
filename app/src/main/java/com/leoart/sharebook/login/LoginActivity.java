package com.leoart.sharebook.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.leoart.sharebook.Constants;
import com.leoart.sharebook.R;
import com.leoart.sharebook.ui.MainActivity;
import com.leoart.sharebook.registration.RegistrationActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_INTERNET = 1;

    private static final String TAG = "LoginActivity";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private Button mEmailSignInButton;
    private TextView tvSkip;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        Log.d(TAG, "FacebookSdk init");
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        initUI();

        loginPresenter = new LoginPresenter(this);
    }

    private void initUI(){
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        tvSkip = (TextView) findViewById(R.id.tvSkip);

        tvSkip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //TODO REFACTOR!!!
        TextView tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        TextView tv_create_account = (TextView) findViewById(R.id.tv_create_account);
        tv_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });



        addFacrbookCallback();
    }

    private void addFacrbookCallback() {
        facebookLoginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, loginResult.getAccessToken().getToken());
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                (object, response) -> {
                                    try {
                                        saveUserToSharedPrefs(object);
                                        startMainActivity(object);
                                    } catch(JSONException ex) {
                                        ex.printStackTrace();
                                    }

                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void startMainActivity(JSONObject object) throws JSONException {
        Log.d(TAG, object.getString(Constants.FACEBOOK_MODELFIELD_USER_NAME));
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(Constants.FACEBOOK_MODELFIELD_USER_NAME, object.getString(Constants.FACEBOOK_MODELFIELD_USER_NAME));
//        intent.putExtra(Constants.FACEBOOK_MODELFIELD_USER_EMAIL, object.getString(Constants.FACEBOOK_MODELFIELD_USER_EMAIL));
//        intent.putExtra(Constants.FACEBOOK_MODELFIELD_USER_GENDER, object.getString(Constants.FACEBOOK_MODELFIELD_USER_GENDER));
//        intent.putExtra(Constants.FACEBOOK_MODELFIELD_USER_ID,object.getString(Constants.FACEBOOK_MODELFIELD_USER_ID));
        startActivity(intent);
    }

    private void saveUserToSharedPrefs(JSONObject jsonObject) throws JSONException {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.SHARED_KEY, MODE_PRIVATE).edit();
        editor.putString(Constants.FACEBOOK_MODELFIELD_USER_NAME, jsonObject.getString(Constants.FACEBOOK_MODELFIELD_USER_NAME));
        editor.apply();
    }

    @Override
    public void goToRegistration() {

    }

    @Override
    public void showEmailError() {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}


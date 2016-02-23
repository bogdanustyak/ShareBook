package com.leoart.sharebook.login;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements LoginListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenter(LoginView loginView) {
        if(loginView==null){
            throw new RuntimeException("LoginView needs to be initialized!!!");
        }
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();
    }

    public void validateCredentials(String email, String password){
        loginView.showProgress();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            loginInteractor.makeLogin(email, password, this);
        }
    }

    @Override
    public void onSuccess(JSONObject response) {
        loginView.hideProgress();
        //TODO Refactor!!!
        try {
            loginView.startMainActivity(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String response) {
        loginView.hideProgress();
        loginView.showEmailError();
        loginView.showPasswordError();
    }
}

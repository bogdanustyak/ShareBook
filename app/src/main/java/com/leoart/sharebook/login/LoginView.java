package com.leoart.sharebook.login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Login view interface describes main view flow
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void startMainActivity(JSONObject jsonObject) throws JSONException;
    void goToRegistration();
    void showEmailError();
    void showPasswordError();
}

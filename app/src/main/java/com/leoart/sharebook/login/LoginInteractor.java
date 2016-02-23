package com.leoart.sharebook.login;

import org.json.JSONObject;

public class LoginInteractor {

    public void makeLogin(String userName, String password, LoginListener loginListener){
        loginListener.onSuccess(new JSONObject());
    }
}

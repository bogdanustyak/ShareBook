package com.leoart.sharebook.login;

import org.json.JSONObject;

public interface LoginListener {
    void onSuccess(JSONObject response);
    void onError(String response);
}

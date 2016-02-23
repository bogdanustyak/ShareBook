package com.leoart.sharebook.registration;

public interface ServerResponseListener {
    void onSuccess(String response);
    void onError(String response);
}

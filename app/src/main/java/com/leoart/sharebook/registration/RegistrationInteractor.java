package com.leoart.sharebook.registration;

public class RegistrationInteractor {

    public void registerUserForFirstStep(String email, String password, ServerResponseListener serverResponseListener){
        serverResponseListener.onSuccess("");
    }

    public void registerUserForSecondStep(String firstName, String lastName, String country, String city, String phoneNumber, String avatar, ServerResponseListener serverResponseListener){
        serverResponseListener.onSuccess("");
    }

}

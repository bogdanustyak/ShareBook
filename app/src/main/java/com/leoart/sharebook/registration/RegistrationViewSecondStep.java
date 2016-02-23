package com.leoart.sharebook.registration;

public interface RegistrationViewSecondStep {
    void showProgress();
    void hideProgress();
    void showAvatarError();
    void showFirstNameError(String response);
    void showLastNameError();
    void showCountryError();
    void showCityError();
    void showPhoneNumberError();
    void getStarted();
}

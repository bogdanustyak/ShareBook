package com.leoart.sharebook.registration;

public interface RegistrationViewFirstStep {
    void showEmailError(String response);
    void showPasswordError(String response);
    void showConfirmPasswordError(String response);
    void showLicenseAcceptError();
    void showProgress();
    void hideProgress();
    void goToNextStep();
}

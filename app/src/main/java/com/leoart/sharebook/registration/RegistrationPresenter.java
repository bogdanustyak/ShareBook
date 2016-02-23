package com.leoart.sharebook.registration;

import android.content.Context;
import android.text.TextUtils;

import com.leoart.sharebook.R;

import org.w3c.dom.Text;

import java.io.File;

public class RegistrationPresenter implements ServerResponseListener{

    private Context context;
    private RegistrationViewFirstStep firstStepView;
    private RegistrationViewSecondStep secondStepView;

    private RegistrationInteractor interactor;

    public RegistrationPresenter(Context context, RegistrationViewFirstStep registrationViewFirstStep) {
        this.context = context;
        this.firstStepView = registrationViewFirstStep;
        this.interactor = new RegistrationInteractor();
    }

    public RegistrationPresenter(Context context, RegistrationViewSecondStep registrationViewSecondStep) {
        this.context = context;
        this.secondStepView = registrationViewSecondStep;
        this.interactor = new RegistrationInteractor();
    }

    public RegistrationPresenter(Context context, RegistrationViewFirstStep registrationViewFirstStep, RegistrationViewSecondStep registrationViewSecondStep) {
        this.context = context;
        this.firstStepView = registrationViewFirstStep;
        this.secondStepView = registrationViewSecondStep;
        this.interactor = new RegistrationInteractor();
    }


    public void validateCredentials(String email, String password, String confirmPassword){
        if(firstStepView!=null)
            firstStepView.showProgress();
        if(!isValidEmail(email)) {
            firstStepView.showEmailError(context.getString(R.string.error_invalid_email));
        }else if( !isValidPassword(password, confirmPassword)) {
            firstStepView.showPasswordError(context.getString(R.string.error_incorrect_password));
            firstStepView.showConfirmPasswordError("Password was not confirmed");
        } else {
            interactor.registerUserForFirstStep(email, password, this);
        }
    }

    public void validateCredentials(String firstName, String lastName, String country, String city, String phoneNumber, String avatar){
        if(secondStepView!=null) {
            secondStepView.showProgress();
        }
        //TODO!!!
//        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(country) && !TextUtils.isEmpty(city)
//                && !TextUtils.isEmpty(phoneNumber) && avatarIsValid(avatar)){
            interactor.registerUserForSecondStep(firstName, lastName, country, city, phoneNumber, avatar, this);
        //}
    }

    private boolean isValidPassword(String password, String confirmation) {
        if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmation) && password.equals(confirmation)){
            //TODO add password validation
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        if(!TextUtils.isEmpty(email)){
            //TODO add email validation
            return true;
        }
        return false;
    }


    private boolean avatarIsValid(String avatarPath) {
        if(!TextUtils.isEmpty(avatarPath)){
            return new File((avatarPath)).exists();
        }
        return false;
    }

    @Override
    public void onSuccess(String response) {
        if(firstStepView!=null) {
            firstStepView.hideProgress();
            firstStepView.goToNextStep();
        }
        if(secondStepView!=null) {
            secondStepView.hideProgress();
            secondStepView.getStarted();
        }

    }

    @Override
    public void onError(String response) {
        if(firstStepView!=null)
            firstStepView.hideProgress();
        if(secondStepView!=null)
            secondStepView.hideProgress();
    }
}

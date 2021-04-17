package com.example.loginform;

import com.example.loginform.data.LoginCredentials;
import com.example.loginform.data.LoginInteractor;

public class LoginPresenter implements Contract.LoginListener {

    private Contract.LoginView loginView;

    private LoginInteractor loginInteractor;

    public LoginPresenter(Contract.LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor(this);
    }

    public void start(LoginCredentials credentials) {
        loginView.showProgressbar();
        loginInteractor.login(credentials);
    }

    @Override
    public void onSuccess() {
        loginView.hideProgressbar();
        loginView.onSuccess();
    }

    @Override
    public void onFailed(String messsage) {
        loginView.hideProgressbar();
        loginView.onFailed(messsage);
    }
}
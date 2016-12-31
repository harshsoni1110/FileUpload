package com.harsh.fileupload.Login;

import android.content.Context;

import com.harsh.fileupload.ServiceGenerators.ServiceGenerator;

/**
 * Created by harsh on 31/12/16.
 */

public class LoginPresenter implements LoginScreen.presenter {

    private String mUserName;
    private String mPassword;
    private Context mView;

    public LoginPresenter(Context mView) {
        this.mView = mView;
    }

    @Override
    public void doLogin() {
        LoginScreen.LoginHTTP loginService = ServiceGenerator.createService(LoginScreen.LoginHTTP.class);

    }

    @Override
    public void setUserNamePassword(String userName, String password) {
        this.mUserName = userName;
        this.mPassword = password;
    }
}

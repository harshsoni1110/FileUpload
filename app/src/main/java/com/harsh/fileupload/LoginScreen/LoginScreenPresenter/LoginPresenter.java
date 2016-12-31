package com.harsh.fileupload.LoginScreen.LoginScreenPresenter;

/**
 * Created by harsh on 31/12/16.
 */

public class LoginPresenter {

    private String mUser;
    private String mPassword;
    public void doLogin () {
        //Go For Login
    }

    public void setUserNamePassword (String userName, String password){
        this.mUser = userName;
        this.mPassword = password;
    }

    public void getOAuthToken (){

    }

    public String getUserName (){
        return mUser;
    }
}

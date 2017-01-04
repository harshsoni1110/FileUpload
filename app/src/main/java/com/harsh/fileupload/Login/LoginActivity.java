package com.harsh.fileupload.Login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.harsh.fileupload.R;

/**
 * Created by harsh on 31/12/16.
 */

public class LoginActivity extends AppCompatActivity implements LoginScreen.view, LoginFragment.CallBacksLoginFragment, SignupFragment.CallBacksSignupFragment{

    private static final String TAG = LoginActivity.class.getName();
    LoginPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(LoginActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void stopProcess() {

    }

    @Override
    public void movesToNext() {

    }

    @Override
    public void showSignup() {
        Log.d(TAG,"Viewing singup");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignupFragment signupFragment = new SignupFragment();
        
        LoginFragment loginFragment = (LoginFragment) fragmentManager.findFragmentById(R.id.fragmentLoginIn);
        fragmentTransaction.hide(loginFragment);
        fragmentTransaction.add(R.id.activity_main, signupFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void showLogin() {
        Log.d(TAG,"Viewing Login");

    }
}

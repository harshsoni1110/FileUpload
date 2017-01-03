package com.harsh.fileupload.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.harsh.fileupload.R;

/**
 * Created by harsh on 31/12/16.
 */

public class LoginActivity extends AppCompatActivity implements LoginScreen.view{

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
}

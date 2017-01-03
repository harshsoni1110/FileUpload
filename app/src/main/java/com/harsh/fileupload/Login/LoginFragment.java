package com.harsh.fileupload.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harsh.fileupload.R;

/**
 * Created by harsh on 3/1/17.
 */

public class LoginFragment extends Fragment {

    public LoginFragment() {
        super();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.login_screen,container,false);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    public void goToSignUp (View view){
        Log.d("Signup","Clicked");

    }

}

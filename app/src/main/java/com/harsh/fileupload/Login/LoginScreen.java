package com.harsh.fileupload.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;


/**
 * Created by harsh on 31/12/16.
 */

public interface LoginScreen {

    interface view {
        void showProcess();
        void stopProcess();
        void movesToNext();
    }

    interface presenter{
        void doLogin();
        void setUserNamePassword(String userName, String password);
    }


    interface LoginHTTP {
        @POST("login.php")
        Call<ResponseBody> getCertificate (String userName,
                                           String password);
    }
}

package com.harsh.fileupload;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.harsh.fileupload.Beans.Contributors;
import com.harsh.fileupload.HTTPService.FileService;
import com.harsh.fileupload.HTTPService.GitHubContributorsService;
import com.harsh.fileupload.ServiceGenerators.ServiceGenerator;
import com.harsh.fileupload.Services.UploadService;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {

/*    private  static String TAG = MainActivity.class.getName();
    TextView text;
    private static OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                String output = bundle.getString("Output");
                Log.d("MAINACTIVTY",output);
                Toast.makeText(MainActivity.this,"Upload Result",Toast.LENGTH_LONG).show();
            }
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 /*       text  = (TextView) findViewById(R.id.txtTest);
*/
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        GitHubContributorsService service = retrofit.create(GitHubContributorsService.class);
        Call <List<Contributors>> contributors = service.getContributors();
        contributors.enqueue(new Callback<List<Contributors>>() {
            @Override
            public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                okhttp3.Response raw = response.raw();
            }

            @Override
            public void onFailure(Call<List<Contributors>> call, Throwable t) {

            }
        });
*/
       /* try {
            List<Contributors> contributorsList = contributors.execute().body();
        }catch (IOException e){

        }*/

/*        LoginService loginService = ServiceGenerator.createService(LoginService.class,"harshsoni1110","harsh29241611");
        loginService.basicLogin(new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d(TAG,user.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG,error.toString());
            }
        });*/
/*
            GitHubContributorsService contributorsClient = ServiceGenerator.createService(GitHubContributorsService.class);

            List <Contributors> contributorsList = contributorsClient.getContributors("fs_opensource", "android-boilerplate");

            for (Contributors contributor: contributorsList){
                Log.d("LOG", contributor.getContributor());
                ((TextView)findViewById(R.id.txtTest)).setText(contributor.toString());
            }
*/

    }

    public void clickHandler(View view) {

  /*      Intent i = new Intent(this, UploadService.class);
        i.putExtra("KEY1","Value");
        startService(i);
        text.setText ("Service Started");*/
    }



    @Override
    protected void onResume() {
        super.onResume();
        /*registerReceiver(receiver, new IntentFilter(
                UploadService.NOTIFICATION));*/
    }
}

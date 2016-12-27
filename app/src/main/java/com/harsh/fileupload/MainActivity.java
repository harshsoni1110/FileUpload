package com.harsh.fileupload;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.harsh.fileupload.Beans.Contributors;
import com.harsh.fileupload.HTTPService.FileService;
import com.harsh.fileupload.HTTPService.GitHubContributorsService;
import com.harsh.fileupload.ServiceGenerators.ServiceGenerator;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private  static String TAG = MainActivity.class.getName();
    private static OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.txtTest);
        GitHubContributorsService service = ServiceGenerator.createService(GitHubContributorsService.class);
        Call<String> call = service.getContributors();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //okhttp3.Response raw = response.raw();

                if (response.isSuccessful()){
                    String str = response.body().toString();
                    //textView.setText(str);
                    Log.d("STRT",str);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("ERROR ---------------", t.toString());
               // textView.setText(t.toString());
            }
        });

        textView.setText(Environment.getExternalStorageDirectory().getAbsolutePath());
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download-512.png");
        uploadFile(Uri.fromFile(file));
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

    private  void uploadFile (Uri fileUri){
        final TextView text = (TextView) findViewById(R.id.txtTest);
        FileService fileService = ServiceGenerator.createService(FileService.class);

        File file = new File(fileUri.getPath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload",file.getName(), requestFile);

        String descriptionString = "Hello this is description speaking";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        Call <ResponseBody> call = fileService.upload(description,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                text.setText(response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                text.setText("Failed upload.");
            }
        });

    }
}

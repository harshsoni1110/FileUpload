package com.harsh.fileupload.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.harsh.fileupload.HTTPService.FileService;
import com.harsh.fileupload.HTTPService.GitHubContributorsService;
import com.harsh.fileupload.MainActivity;
import com.harsh.fileupload.R;
import com.harsh.fileupload.ServiceGenerators.ServiceGenerator;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harsh on 27/12/16.
 */

public class UploadService extends IntentService {


    public UploadService (){
        super("UPloadService");
    }
    public UploadService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {


        //textView.setText(Environment.getExternalStorageDirectory().getAbsolutePath());
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/File_Contribute1.png");
        try {
            String response = uploadFile(Uri.fromFile(file));
            publishResults(response);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private  String uploadFile (Uri fileUri) throws IOException {

        Intent notificationIntent = new Intent(this, UploadService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);


        //final TextView text = (TextView) findViewById(R.id.txtTest);

        FileService fileService = ServiceGenerator.createService(FileService.class);
        Response<ResponseBody> response = null;
        for (int i = 0 ; i < 5 ; i++){
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Notification Title")
                    .setContentText((i+1)+"/5")
                    .setSmallIcon(R.drawable.download_image)
                    .setContentIntent(pendingIntent)
                    .setTicker("Ticket text")
                    .build();

            startForeground(1, notification);
            File file = new File(fileUri.getPath());
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload",file.getName(), requestFile);

            String descriptionString = "Hello this is description speaking";
            RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
            Call<ResponseBody> call = fileService.upload(description,body);
            response = call.execute();
            if (response.isSuccessful()){
                Log.d("UPLOAD SERVICE","SUCCESS");
            }
            else {
                Log.d("UPLOAD SERVICE","FAILED");
            }

        }
        //ResponseBody responseBody = call.execute().body();

        return response.body().toString();
        /*call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                text.setText(response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                text.setText("Failed upload.");
            }
        });*/

    }

    private void publishResults(String outputPath) {
        Intent intent = new Intent();
        intent.putExtra("Output", outputPath);
        //intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }


}

package com.harsh.fileupload.MultiSelect;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.harsh.fileupload.R;

import java.io.IOException;

/**
 * Created by harsh on 7/1/17.
 */

public class MultipleFileService extends IntentService implements MultipleFileSelectPresenter.postFileUploadUpdate {

    private static final String TAG = MultipleFileService.class.getName();
    private static final int NOTIFICATION_ID = 1;
    private MultipleFileSelectPresenter mPrsenter = null;
    private int mNumberOfFiles = 0;
    private int mFilesUploaded = 1;
    private Intent notificationIntent= null;
    private PendingIntent pendingIntent = null;
    Notification notification = null;
    public MultipleFileService (){

        super("MultipleFileService");

    }
    public MultipleFileService(String name) {

        super(name);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SERVICE INTENT","INTENT HANDLE");
        if (mPrsenter != null){
            mNumberOfFiles = mPrsenter.getTotalFiles();
            mPrsenter.processSelectedFiles();
            notification = new Notification.Builder(this)
                    .setContentTitle("Notification Title")
                    .setContentText((mFilesUploaded)+"/"+mNumberOfFiles)
                    .setSmallIcon(R.drawable.download_image)
                    .setContentIntent(pendingIntent)
                    .setTicker("Ticket text")
                    .build();
            startForeground(NOTIFICATION_ID, notification);
            try {
                mPrsenter.uploadSelectedFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mPrsenter = intent.getParcelableExtra(MultipleFileActivity.PRESENTER_TAG);
        mPrsenter.setmFileUpdateListner(this);
        notificationIntent = new Intent(this,MultipleFileService.class);
        pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void postUpdate(int mNumOfFilesUploaded) {
        mFilesUploaded = mNumOfFilesUploaded;
        notifyUser();
    }

    public void notifyUser (){
        //Update progress bar here in notification
        notification = new Notification.Builder(this)
                .setContentTitle("Notification Title")
                .setContentText((mFilesUploaded)+"/"+mNumberOfFiles)
                .setSmallIcon(R.drawable.download_image)
                .setContentIntent(pendingIntent)
                .setTicker("Ticket text")
                .build();
   //     startForeground(NOTIFICATION_ID,notification);
        Log.d(TAG,"Notifying update");
    }
}

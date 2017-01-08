package com.harsh.fileupload.MultiSelect;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by harsh on 7/1/17.
 */

public class MultipleFileService extends IntentService implements MultipleFileSelectPresenter.postFileUploadUpdate {

    private static final String TAG = MultipleFileService.class.getName();
    private MultipleFileSelectPresenter mPrsenter = null;
    private int mNumberOfFiles = 0;
    private int mFilesUploaded = -1;
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
            mPrsenter.uploadSelectedFiles();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mPrsenter = intent.getParcelableExtra(MultipleFileActivity.PRESENTER_TAG);
        mPrsenter.setmFileUpdateListner(this);
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
        Log.d(TAG,"Notifying update");
    }
}

package com.harsh.fileupload.MultiSelect;

import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.harsh.fileupload.ServiceGenerators.ServiceGenerator;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harsh on 5/1/17.
 */

public class MultipleFileSelectPresenter implements MultipleFileSelectScreen.presenter, Parcelable {

    public static final String TAG = MultipleFileSelectPresenter.class.getName();
    private ArrayList <String> listFiles;
    private ArrayList <Uri> listFilesUri;
    private int mNumberOfFiles;
    private int mNumberOfFilesUploaded;
    private postFileUploadUpdate mFileUpdateListner;
    private MultipleFileSelectScreen.FileService mFileService = ServiceGenerator.createService(MultipleFileSelectScreen.FileService.class);

    protected MultipleFileSelectPresenter(Parcel in) {
        listFiles = in.createStringArrayList();
        listFilesUri = in.createTypedArrayList(Uri.CREATOR);
        mNumberOfFiles = in.readInt();
        mNumberOfFilesUploaded = in.readInt();
    }

    public static final Creator<MultipleFileSelectPresenter> CREATOR = new Creator<MultipleFileSelectPresenter>() {
        @Override
        public MultipleFileSelectPresenter createFromParcel(Parcel in) {
            return new MultipleFileSelectPresenter(in);
        }

        @Override
        public MultipleFileSelectPresenter[] newArray(int size) {
            return new MultipleFileSelectPresenter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(listFiles);
        dest.writeTypedList(listFilesUri);
        dest.writeInt(mNumberOfFiles);
        dest.writeInt(mNumberOfFilesUploaded);
    }

    public interface postFileUploadUpdate {
        void postUpdate (int mNumOfFilesUploaded);
    }

    MultipleFileSelectPresenter(){
        Log.d(TAG,"Constructing files");
        listFiles = new ArrayList<String>();
        listFiles.add (Environment.getExternalStorageDirectory().getAbsolutePath()+"/File_Contribute1.png");
        listFiles.add (Environment.getExternalStorageDirectory().getAbsolutePath()+"/download.png");
        listFiles.add (Environment.getExternalStorageDirectory().getAbsolutePath()+"/log.txt");
        if (listFiles.size() > 0){
            listFilesUri = new ArrayList<Uri>();
        }
        mNumberOfFiles = listFiles.size();

        mNumberOfFilesUploaded = -1;
    }
    @Override
    public void processSelectedFiles() {
        for (String filePath: listFiles){
            File fileObj = new File(filePath);
            if (fileObj != null){
                listFilesUri.add(Uri.fromFile(fileObj));
            }
        }
        mNumberOfFiles = listFiles.size();
        Log.d(TAG,mNumberOfFiles+" no of files to upload");
    }

    @Override
    public void uploadSelectedFiles() {
        Log.d(TAG,"File uploading starting");
        Response <ResponseBody> response = null;
        if (mNumberOfFiles > 0){
            mNumberOfFilesUploaded = 0;
            for (Uri fileUri: listFilesUri){
                Log.d(TAG, "uploadSelectedFiles: "+fileUri.getPath());
                uploadFile(new File(fileUri.getPath()));
            }

        }
        stopUploading();
    }

    synchronized void uploadFile (File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload",file.getName(), requestFile);

        String descriptionString = "Hello this is description speaking";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        Call<ResponseBody> call = mFileService.upload(description,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG,"Successful response");
                    if (mFileUpdateListner != null)
                        mFileUpdateListner.postUpdate(++mNumberOfFilesUploaded);
                }
                else {
                    Log.d(TAG,"Unsuccessful response");
                    //Not successful
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Upload failed");
            }
        });

    }
    @Override
    public int getTotalFiles() {
        return mNumberOfFiles;
    }

    @Override
    public int getStatusOfFile() {
        return 0;
    }

    @Override
    public int getUploadStatus() {
        return mNumberOfFilesUploaded;
    }

    @Override
    public boolean isFileUploadInProgress() {
        return mNumberOfFilesUploaded != -1;
    }

    @Override
    public void stopUploading() {
        Log.d(TAG,"Uploading stop");
    }

    public void setmFileUpdateListner (postFileUploadUpdate listner){
        this.mFileUpdateListner = listner;
    }
}

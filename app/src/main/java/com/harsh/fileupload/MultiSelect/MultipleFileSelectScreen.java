package com.harsh.fileupload.MultiSelect;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by harsh on 5/1/17.
 */

public interface MultipleFileSelectScreen {

    public interface view {
        public void showFiles();
    }
    public interface presenter{
        public void processSelectedFiles();
        public void uploadSelectedFiles () throws IOException;
        public int getTotalFiles ();
        public int getStatusOfFile();
        public int getUploadStatus();
        public boolean isFileUploadInProgress();
        public void stopUploading ();
    }

    public interface FileService {
        @Multipart
        @POST("file.php")
        Call<ResponseBody> upload (@Part("description") RequestBody description,
                                   @Part MultipartBody.Part file);
    }
}

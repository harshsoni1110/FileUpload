package com.harsh.fileupload.HTTPService;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by harsh on 23/12/16.
 */

public interface FileService {

    @Multipart
    @POST("file.php")
    Call <ResponseBody> upload (@Part("description")RequestBody description,
                                @Part MultipartBody.Part file);
}

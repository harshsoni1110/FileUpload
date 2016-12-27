package com.harsh.fileupload.HTTPService;



import com.harsh.fileupload.Beans.Contributors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by harsh on 21/12/16.
 */

public interface GitHubContributorsService {
    @GET("/file.php")
    Call<String> getContributors();
}

package com.harsh.fileupload.Beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harsh on 21/12/16.
 */
public class Contributors {
    @SerializedName("login")
    private String contributor;
    @SerializedName("id")
    private String repositories;

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getRepositories() {
        return repositories;
    }

    public void setRepositories(String repositories) {
        this.repositories = repositories;
    }
}

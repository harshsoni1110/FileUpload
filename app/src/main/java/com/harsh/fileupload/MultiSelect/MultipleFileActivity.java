package com.harsh.fileupload.MultiSelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.harsh.fileupload.R;

/**
 * Created by harsh on 7/1/17.
 */

public class MultipleFileActivity extends AppCompatActivity implements MultipleFileSelectScreen.view {
    private static final String TAG = MultipleFileActivity.class.getName();
    public static final String PRESENTER_TAG = "MULTIPLE_FILE_PRESENTER";
    private MultipleFileSelectPresenter mPresenter = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_file);
        mPresenter = new MultipleFileSelectPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void showFiles() {

    }

    public void onClick (View view){
        //Log.d(TAG,"CLICKED");
        Intent i = new Intent(this, MultipleFileService.class);
        i.putExtra(PRESENTER_TAG,  mPresenter);
        startService(i);
    }
}

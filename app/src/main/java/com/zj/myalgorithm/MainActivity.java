package com.zj.myalgorithm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zj.myalgorithm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setPresenter(this);
    }


    //md5加密
    public void onMd5Clicked(){
        Md5Activity.launch(this);
    }

    //
    public void onSHAClicked(){
        SHAActivity.launch(this);
    }
}

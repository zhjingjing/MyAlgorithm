package com.zj.myalgorithm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zj.myalgorithm.databinding.ActivityShaBinding;
import com.zj.myalgorithm.utils.EncryptionUtil;

public class SHAActivity extends AppCompatActivity {
    private ActivityShaBinding binding;
    public static void launch(Context context){
        Intent intent=new Intent(context,SHAActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sha);
        binding.setPresenter(this);
    }


    //sha1
    public void onSha1Clicked(){
        String content=binding.editContent.getText().toString();
        binding.tvResult.setText("SHA-1:"+ EncryptionUtil.getSHA(content,"SHA-1"));
    }
    public void onSha256Clicked(){
        String content=binding.editContent.getText().toString();
        binding.tvResult.setText("SHA-256:"+ EncryptionUtil.getSHA(content,"SHA-256"));
    }

    public void onSha384Clicked(){
        String content=binding.editContent.getText().toString();
        binding.tvResult.setText("SHA-384:"+ EncryptionUtil.getSHA(content,"SHA-384"));
    }
    public void onSha512Clicked(){
        String content=binding.editContent.getText().toString();
        binding.tvResult.setText("SHA-512:"+ EncryptionUtil.getSHA(content,"SHA-512"));
    }
}

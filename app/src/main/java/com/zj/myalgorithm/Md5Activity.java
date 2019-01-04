package com.zj.myalgorithm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.zj.myalgorithm.databinding.ActivityMd5Binding;
import com.zj.myalgorithm.utils.EncryptionUtil;
import com.zj.myalgorithm.utils.ImageUtil;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Md5Activity extends AppCompatActivity {
    private ActivityMd5Binding binding;

    public static void launch(Context context){
        Intent intent=new Intent(context,Md5Activity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_md5);
        binding.setPresenter(this);
    }

    //加密
    public void onEncyptionClicked(){
        String content=binding.editContent.getText().toString();
        String salt=binding.editSalt.getText().toString();
        if (!TextUtils.isEmpty(content)){
            String lowResult= EncryptionUtil.md5(content,true);
            String upperResult= EncryptionUtil.md5(content,false);

            String lowSalt=EncryptionUtil.md5(content,salt,true);
            String upperSalt=EncryptionUtil.md5(content,salt,false);
            binding.tvResult.setText("小写："+lowResult+"\n大写:"+upperResult+"\n加盐小写："+lowSalt+"\n加盐大写："+upperSalt
            );
        }

    }



}

package com.zj.myalgorithm.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * create by zj on 2019/1/3
 */
public class EncryptionUtil {

    //根据资源id转uri
    public static Uri resourceIdToUri(Context context, int resourceId) {
        Resources r =context.getResources();
        Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resourceId) + "/"
                + r.getResourceTypeName(resourceId) + "/"
                + r.getResourceEntryName(resourceId));
        return uri;
    }


    /**
     * @param string 代价密字符串
     * @param isLowcase 是否小写
     * @return
     */
    public static String md5(String string,boolean isLowcase){
        if (TextUtils.isEmpty(string)){
            return "";
        }
        MessageDigest md5=null;
        try {
            md5=MessageDigest.getInstance("MD5");
            byte[] bytes=  md5.digest(string.getBytes());

            StringBuilder result = new StringBuilder();

            for (byte b:bytes){
                String temp=Integer.toHexString(b&0xff);//b&0xff转int 再转string
                if (temp.length()==1){
                    temp="0"+temp;
                }
                result.append(temp);
            }

            //返回值大小写
            return isLowcase?result.toString().toLowerCase():result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param string
     * @param slat 加盐
     * @param isLowcase
     * @return
     */
    public static String md5(String string,String slat,boolean isLowcase){
        if (TextUtils.isEmpty(string)){
            return "";
        }

        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte[] bytes=  md5.digest((string+slat).getBytes());

            StringBuilder result = new StringBuilder();

            for (byte b:bytes){
                String temp=Integer.toHexString(b&0xff);
                if (temp.length()==1){
                    temp="0"+temp;
                }
                result.append(temp);
            }

            //返回值大小写
            return isLowcase?result.toString().toLowerCase():result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     *
     * 获取文件的md5
     * @param file
     * @param isLowcase
     * @return
     */
    public static String md5(File file,boolean isLowcase) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[1024*8];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isLowcase?result.toLowerCase():result.toUpperCase();
    }


    /**
     *
     * sha
     * @param authString
     * @param shaType  SHA-1,SHA-256,SHA-384,SHA-512
     * @return
     */
    public static String getSHA(String authString,String shaType) {
        try {
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance(shaType);
            md.update(authString.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            Log.e(shaType,e.toString());
            return "";

        }
    }


}

package com.dame.ordinarymvp;

import android.os.Environment;

import java.io.File;

public class Constant {

    public static final String PROJECT_NAME = "Friotsapp";
    public static final String BASE_FILE_CACHE_URL = Environment.getExternalStorageDirectory().toString() + File.separator + PROJECT_NAME;
    public static final String IMAGE_CACHE = BASE_FILE_CACHE_URL + File.separator + "image" + File.separator + "temp";   //图片缓存路径

    //测试地址一
    public static final String BASE_WWW = "https://www.mxnzp.com/api/";
    public static final String BASE_URL = "";


}

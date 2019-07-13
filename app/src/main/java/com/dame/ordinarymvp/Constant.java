package com.dame.ordinarymvp;

import android.os.Environment;

import java.io.File;

public class Constant {

    public static final String PROJECT_NAME = "Friotsapp";
    public static final String BASE_FILE_CACHE_URL = Environment.getExternalStorageDirectory().toString() + File.separator + PROJECT_NAME;
    public static final String IMAGE_CACHE = BASE_FILE_CACHE_URL + File.separator + "image" + File.separator + "temp";   //图片缓存路径

    //测试地址一
//    public static final String BASE_WWW = "http://syc320.320.io:23416/api";
//    public static final String BASE_URL = "http://syc320.320.io:23416/";
    public static final String BASE_WWW = "http://192.168.1.220:80/api";
    public static final String BASE_URL = "http://192.168.1.220:80/";
    //测试地址二
//    public static final String BASE_WWW = "http://192.168.1.228:8008/api";
//    public static final String BASE_URL = "http://192.168.1.228:8008/";
//    public static final String BASE_WWW = "http://2547l265b6.qicp.vip:38983/api";
//    public static final String BASE_URL = "http://2547l265b6.qicp.vip:38983/";

}

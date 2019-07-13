package com.dame.ordinarymvp.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.dame.ordinarymvp.Constant;
import com.dame.ordinarymvp.base.BaseApplication;
import com.dame.ordinarymvp.http.converter.GsonConverterBodyFactory;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BaseRetrofit {

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    private static Retrofit retrofit;


    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return null != info && info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 初始化网络通信服务
     */

    public static Retrofit init() {
        if (retrofit == null) {
            synchronized (BaseRetrofit.class) {
                if (retrofit == null) {
                    //指定缓存路径，缓存大小
                    Cache cache = new Cache(BaseApplication.getContext().getCacheDir(), 1024 * 1024 * 100);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .retryOnConnectionFailure(true)
                            .addInterceptor(sLoggingInterceptor)
                            .addInterceptor(sRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                            .connectTimeout(15, TimeUnit.SECONDS)//连接超时时间
                            .readTimeout(15, TimeUnit.SECONDS)//读取超时时间
                            .writeTimeout(15, TimeUnit.SECONDS)//写入超时时间
                            .retryOnConnectionFailure(false)//连接不上是否重连,false不重连
                            .build();

                    retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterBodyFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(Constant.BASE_WWW)
                            .build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 响应头拦截器，配置缓存策略
     */

    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkAvailable(BaseApplication.getContext())) {
//            CacheControl.FORCE_CACHE; //仅仅使用缓存   CacheControl.FORCE_NETWORK; 仅仅使用网络
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.e("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (isNetworkAvailable(BaseApplication.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /**
     * 打印返回的json数据拦截器
     */
    private static Interceptor sLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                Logger.d("LogTAG", "request.body()==null");
            }
            //打印url信息
            Logger.w(request.url() + (request.body() != null ? "?" + parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);
            return response;
        }
    };

    @NonNull//参数非空
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }


}

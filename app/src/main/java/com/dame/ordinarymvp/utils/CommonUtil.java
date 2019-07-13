package com.dame.ordinarymvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;


import com.dame.ordinarymvp.base.BaseApplication;

import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    /**
     * 空判断
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == "" || str == null || str.length() == 0 || "null".equalsIgnoreCase(str.toString()))
            return true;
        else
            return false;
    }

    /**
     * 启动对应的功能模块
     */
    public static void startAct(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 判断手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        //^((\d{7,8})|(0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$
        Pattern p = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
        // return true;
    }


    /**
     * 判断是否登录
     *
     * @param context
     * @return
     */
    public static boolean checkLogin(Context context) {
        boolean result = false;

        String token = PreferencesUtils.getString(context, "token");
        if (!isEmpty(token)) {
            return true;
        } else {
//            CommonUtil.startAct(context, LoginActivity.class);
        }

        return result;
    }

    /**
     * 跳转到权限设置界面
     */
    public static void applicationDetailsSettings(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 获取Drawable
     * @param resid resid
     * @return Drawable
     */
    public static Drawable getDrawable(int resid) {
        return getResource().getDrawable(resid);
    }

    /**
     * 获取颜色
     * @param resid resid
     * @return int
     */
    public static int getColor(int resid) {
        return getResource().getColor(resid);
    }

    /**
     * 获取resources
     * @return Resources
     */
    public static Resources getResource() {
        return BaseApplication.getContext().getResources();
    }

    /**
     * 获取数组
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResource().getStringArray(resId);
    }

    /**
     * 获取string字符串
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    /**
     * 获取Dimens
     * @param resId resId
     * @return float
     */
    public static float getDimens(int resId) {
        return getResource().getDimension(resId);
    }


    /**
     * 设置固定字段颜色
     */
    public static SpannableStringBuilder spann(Context context, int colors, int start, int end, String content) {
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(colors)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//SPAN_INCLUSIVE_EXCLUSIVE  SPAN_EXCLUSIVE_EXCLUSIVE
        return builder;
    }

    /**
     * 固定字段大小
     * @param context
     * @param size
     * @param start
     * @param end
     * @param content
     * @return
     */
    public static SpannableStringBuilder spannFootSize(Context context, float size, int start, int end, String content){
        SpannableStringBuilder span = new SpannableStringBuilder(content);
        span.setSpan(new RelativeSizeSpan(size), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }


    /**
     * 手机号号替换，保留前三位和后四位
     * 如果身手机号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param phone 手机号
     */
    public static String phoneReplaceWithStar(String phone) {

        if (phone == null || phone.isEmpty()) {
            return null;
        } else {
            return replaceAction(phone, "(?<=\\d{3})\\d(?=\\d{4})");
        }
    }

    private static String replaceAction(String str, String regular) {
        return str.replaceAll(regular, "*");
    }


    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }

    /**
     * 获取当前APP缓存
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除当前APP缓存
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//        return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}

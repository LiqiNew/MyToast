package com.liqi.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

/**
 * toast显示工具操作对象
 * <p>
 * Created by LiQi on 2017/10/25.
 */
public class MyToast {

    /**
     * 系统toast长时间显示
     *
     * @param context
     * @param contentInt
     */
    public static void showLong(Context context, int contentInt) {
        if (!Toast.makeText(context, contentInt, ToastTime.LENGTH_LONG).show()) {
            android.widget.Toast.makeText(context, contentInt, android.widget.Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 系统toast长时间显示
     *
     * @param context
     * @param contentStr
     */
    public static void showLong(Context context, String contentStr) {
        if (!Toast.makeText(context, contentStr, ToastTime.LENGTH_LONG).show()) {
            android.widget.Toast.makeText(context, contentStr, android.widget.Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 系统toast短时间显示
     *
     * @param context
     * @param contentInt
     */
    public static void showShort(Context context, int contentInt) {
        if (!Toast.makeText(context, contentInt, ToastTime.LENGTH_SHORT).show()) {
            android.widget.Toast.makeText(context, contentInt, android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 系统toast短时间显示
     *
     * @param context
     * @param contentStr
     */
    public static void showShort(Context context, String contentStr) {
        if (!Toast.makeText(context, contentStr, ToastTime.LENGTH_SHORT).show()) {
            android.widget.Toast.makeText(context, contentStr, android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 返回自定义toast
     *
     * @param context
     * @param view         布局View
     * @param toastTime    显示持续时长
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    private static void putDiyToast(Context context, View view, ToastTime toastTime,
                                    int transparency, int gravity, int xOffset, int yOffset) {
        if (!Toast.putCenterToast(context, view, toastTime, transparency, gravity, xOffset, yOffset).show()) {
            Toast.aNativeToast(context, view, toastTime, transparency, gravity, xOffset, yOffset).show();
        }
    }


    /**
     * 返回自定义toast居中不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putCenterDiyToastLong(Context context, View view, int transparency) {
        putDiyToastLong(context, view, transparency, Gravity.CENTER);
    }

    /**
     * 返回自定义toast居中长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putCenterDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastLong(context, view, transparency, Gravity.CENTER, xOffset, yOffset);
    }


    /**
     * 返回自定义toast顶部不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putTopDiyToastLong(Context context, View view, int transparency) {
        putDiyToastLong(context, view, transparency, Gravity.TOP);
    }

    /**
     * 返回自定义toast顶部长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putTopDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastLong(context, view, transparency, Gravity.TOP, xOffset, yOffset);
    }

    /**
     * 返回自定义toast底部不偏距长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putBottomDiyToastLong(Context context, View view, int transparency) {
        putDiyToastLong(context, view, transparency, Gravity.BOTTOM);
    }

    /**
     * 返回自定义toast底部长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putBottomDiyToastLong(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastLong(context, view, transparency, Gravity.BOTTOM, xOffset, yOffset);
    }

    /**
     * 返回自定义toast居中不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putCenterDiyToastShort(Context context, View view, int transparency) {
        putDiyToastShort(context, view, transparency, Gravity.CENTER);
    }

    /**
     * 返回自定义toast居中短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putCenterDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastShort(context, view, transparency, Gravity.CENTER, xOffset, yOffset);
    }

    /**
     * 返回自定义toast顶部不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putTopDiyToastShort(Context context, View view, int transparency) {
        putDiyToastShort(context, view, transparency, Gravity.TOP);
    }

    /**
     * 返回自定义toast顶部短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putTopDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastShort(context, view, transparency, Gravity.TOP, xOffset, yOffset);
    }

    /**
     * 返回自定义toast底部不偏距短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @return
     */
    public static void putBottomDiyToastShort(Context context, View view, int transparency) {
        putDiyToastShort(context, view, transparency, Gravity.BOTTOM);
    }

    /**
     * 返回自定义toast底部短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putBottomDiyToastShort(Context context, View view, int transparency, int xOffset, int yOffset) {
        putDiyToastShort(context, view, transparency, Gravity.BOTTOM, xOffset, yOffset);
    }

    /**
     * 返回自定义toast短时间不偏距显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @return
     */
    public static void putDiyToastShort(Context context, View view, int transparency, int gravity) {
        putDiyToast(context, view, ToastTime.LENGTH_SHORT, transparency, gravity, 0, 0);
    }

    /**
     * 返回自定义toast短时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putDiyToastShort(Context context, View view, int transparency, int gravity, int xOffset, int yOffset) {
        putDiyToast(context, view, ToastTime.LENGTH_SHORT, transparency, gravity, xOffset, yOffset);
    }

    /**
     * 返回自定义toast长时间不偏距显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @return
     */
    public static void putDiyToastLong(Context context, View view, int transparency, int gravity) {
        putDiyToast(context, view, ToastTime.LENGTH_LONG, transparency, gravity, 0, 0);
    }

    /**
     * 返回自定义toast长时间显示
     *
     * @param context
     * @param view         布局View
     * @param transparency 透明度 0-255
     * @param gravity      视图显示位置
     * @param xOffset      X轴偏距
     * @param yOffset      Y轴偏距
     * @return
     */
    public static void putDiyToastLong(Context context, View view, int transparency, int gravity, int xOffset, int yOffset) {
        putDiyToast(context, view, ToastTime.LENGTH_LONG, transparency, gravity, xOffset, yOffset);
    }
}

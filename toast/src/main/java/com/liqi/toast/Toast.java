package com.liqi.toast;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Toast处理对象
 * Created by LiQi on 2017/10/25.
 */

public class Toast {

    private Object mToast;

    private Toast(Context context, String message, ToastTime toastTime) {
        int checkNotification = getCheckNotification(context);

        if (checkNotification == 1) {
            mToast = LToast.makeText(context, message, toastTime);
        } else {

            int duration;
            switch (toastTime) {
                case LENGTH_SHORT:
                    duration = android.widget.Toast.LENGTH_SHORT;
                    break;
                case LENGTH_LONG:
                    duration = android.widget.Toast.LENGTH_LONG;
                    break;
                default:
                    duration = android.widget.Toast.LENGTH_SHORT;
                    break;
            }
            mToast = android.widget.Toast.makeText(context, message, duration);
        }
    }

    private Toast(Context context, int resId, ToastTime toastTime) {
        int checkNotification = getCheckNotification(context);

        if (checkNotification == 1) {
            mToast = LToast.makeText(context, resId, toastTime);
        } else {

            int duration;
            switch (toastTime) {
                case LENGTH_SHORT:
                    duration = android.widget.Toast.LENGTH_SHORT;
                    break;
                case LENGTH_LONG:
                    duration = android.widget.Toast.LENGTH_LONG;
                    break;
                default:
                    duration = android.widget.Toast.LENGTH_SHORT;
                    break;
            }
            mToast = android.widget.Toast.makeText(context, resId, duration);
        }
    }

    private Toast() {

    }

    private Toast(Context context, View view, ToastTime toastTime,
                  int transparency,int gravity,int xOffset, int yOffset) {
        int checkNotification = getCheckNotification(context);
        if (checkNotification == 1) {
            LToast lToast = LToast.getLToast(context);
            // 设置透明度
            view.getBackground().setAlpha(transparency);
            lToast.setContentView(view);
            lToast.setGravity(gravity, xOffset, yOffset);
            lToast.setDuration(toastTime);
            mToast = lToast;
        } else {
            mToast = aNativeToast(context, view, toastTime, transparency,gravity,xOffset,yOffset);
        }
    }

    public static Toast makeText(Context context, String message, ToastTime toastTime) {
        return new Toast(context, message, toastTime);
    }

    public static Toast makeText(Context context, int resId, ToastTime toastTime) {
        return new Toast(context, resId, toastTime);
    }

    public static Toast putCenterToast(Context context, View view, ToastTime toastTime,
                                       int transparency,int gravity,int xOffset, int yOffset) {
        return new Toast(context, view, toastTime, transparency,gravity,xOffset,yOffset);
    }

    public static android.widget.Toast aNativeToast(Context context, View view,
                                                    ToastTime toastTime, int transparency,int gravity, int xOffset, int yOffset) {
        android.widget.Toast toast = new android.widget.Toast(context);
        // 设置透明度
        view.getBackground().setAlpha(transparency);
        toast.setView(view);
        toast.setGravity(gravity, xOffset, yOffset);

        int duration;
        switch (toastTime) {
            case LENGTH_SHORT:
                duration = android.widget.Toast.LENGTH_SHORT;
                break;
            case LENGTH_LONG:
                duration = android.widget.Toast.LENGTH_LONG;
                break;
            default:
                duration = android.widget.Toast.LENGTH_SHORT;
                break;
        }
        toast.setDuration(duration);
        return toast;
    }

    private int getCheckNotification(Context context) {
        int checkNotification;
        if (context instanceof Application) {
            checkNotification = 0;
        } else {
            checkNotification = isNotificationEnabled(context) ? 0 : 1;
        }
        return checkNotification;
    }

    /**
     * 用来判断是否开启通知权限
     */
    private boolean isNotificationEnabled(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();

            String pkg = context.getApplicationContext().getPackageName();

            int uid = appInfo.uid;

            Class appOpsClass; /* Context.APP_OPS_MANAGER */

            try {
                appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (int) opPostNotificationValue.get(Integer.class);
                return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean show() {
        if (mToast instanceof LToast) {
            return ((LToast) mToast).show();
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).show();
            return true;
        } else {
            return false;
        }
    }

    public void cancel() {
        if (mToast instanceof LToast) {
            ((LToast) mToast).cancel();
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).cancel();
        }
    }

    public Toast setText(CharSequence s) {
        if (mToast instanceof LToast) {
            ((LToast) mToast).setText(s);
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).setText(s);
        }
        return this;
    }
}
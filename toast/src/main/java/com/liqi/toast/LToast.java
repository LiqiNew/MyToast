package com.liqi.toast;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.liqi.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义toast
 * <p>
 * Context 依赖于栈顶的Context才能显示
 * <p>
 * 如果自定义toast视图还在显示，而依赖的activity却销毁了，会报：android.view.WindowLeaked:
 * 请无视这个错误提示，因为在指定的时间过后会自动释放的。
 * <p>
 * Created by LiQi on 2017/10/25.
 */
public class LToast {

    private static Timer timer;
    private static View contentView;


    private final Handler handler;
    private WindowManager manger;
    private Long time;

    private WindowManager.LayoutParams params;
    private CharSequence text;
    //获取toast系统的布局弹出内容控件ID
    private int mTextViewId = getIdentifier("message", "id");

    {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                cancel();
            }
        };
    }

    private LToast(Context context) {
        this(context, "", ToastTime.LENGTH_SHORT);
        mTextViewId = -1;
    }

    private LToast(Context context, CharSequence text, ToastTime toastTime) {
        manger = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        cancel();

        initWindow();
        initView(context);

        setDuration(toastTime);
        setText(text);
    }

    public static LToast getLToast(Context context) {
        return new LToast(context);
    }

    public static LToast makeText(Context context, String text, ToastTime toastTime) {
        return new LToast(context, text, toastTime);
    }

    public static LToast makeText(Context context, int resId, ToastTime toastTime) {
        String textContent = "";
        try {
            CharSequence text = context.getText(resId);
            textContent = text.toString();
        } catch (Exception e) {
            Logger.e("LToast异常", "传入的resId值不符合规则");
        }
        return makeText(context, textContent, toastTime);
    }

    private void initWindow() {
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        //获取toast系统的样式
        params.windowAnimations = getIdentifier("Animation_Toast", "style");
        params.setTitle("LToast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.y = 200;
    }

    private void initView(Context context) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //获取toast系统的布局
        int layout = getIdentifier("transient_notification", "layout");
        try {
            contentView = inflate.inflate(layout, null);
        } catch (Exception e) {
            contentView = null;
        }
    }

    /**
     * 显示出来
     *
     * @return
     */
    public boolean show() {
        if (null != contentView) {
            manger.addView(contentView, params);
            if (mTextViewId != -1) {
                setContentText();
            }

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            }, time);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 清除屏幕显示
     */
    public void cancel() {
        try {
            if (null != contentView && null != manger) {
                manger.removeView(contentView);
            }
        } catch (IllegalArgumentException e) {
            //这边由于上下文被销毁后removeView可能会抛出IllegalArgumentException
            //暂时这么处理，因为是轻量级的，不想和Context上下文的生命周期绑定在一块儿
            //其实如果真的想这么做，可以参考博文2的第一种实现方式，添加一个空的fragment来做生命周期绑定
        }
        if (null != timer) {
            timer.cancel();
            timer = null;
        }

        contentView = null;
        System.gc();
    }

    private void setContentText() {
        try {
            TextView view = (TextView) contentView.findViewById(mTextViewId);
            view.setText(text);

        } catch (Exception e) {
            Logger.e("LToast异常", "传入的Id非TextView或者其子类");

        }
    }

    /**
     * 设置显示的时长
     *
     * @param toastTime
     */
    public void setDuration(ToastTime toastTime) {
        switch (toastTime) {
            case LENGTH_SHORT:
                time = 2000L;
                break;
            case LENGTH_LONG:
                time = 3500L;
                break;
            default:
                time = 2000L;
                break;
        }
    }

    /**
     * 设置显示视图
     *
     * @param contentView
     */
    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    /**
     * 设置改变的视图TextView控件ID
     *
     * @param textViewId
     */
    public void setTextViewId(int textViewId) {
        mTextViewId = textViewId;
    }

    /**
     * Set the location at which the notification should appear on the screen.
     *
     * @see Gravity
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {
        final Configuration config = contentView.getContext().getResources().getConfiguration();
        final int gravityConfig = Gravity.getAbsoluteGravity(gravity, config.getLayoutDirection());
        params.gravity = gravityConfig;
        params.x = xOffset;
        params.y = yOffset;
    }

    /**
     * 设置显示内容
     *
     * @param s
     */
    public void setText(CharSequence s) {
        text = s;
    }

    /**
     * 通过字段获取android内部资源
     *
     * @param name 资源名称
     * @param type 资源类型
     * @return
     */
    private int getIdentifier(String name, String type) {
        return Resources.getSystem().getIdentifier(name, type, "android");
    }
}
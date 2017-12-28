package com.liqi.mytoast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.liqi.toast.MyToast;

/**
 * 视图操作相关的例子
 * <p>
 * MVC模式
 * 注意看类注释和调用方法内注释
 * </p>
 * Created by LiQi on 2017/12/9.
 */

public class TestShowWindowActivity extends AppCompatActivity implements View.OnClickListener {
    private int mToastType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show_window_activity);
        findViewById(R.id.test_show_window_button2).setOnClickListener(this);
        findViewById(R.id.test_show_window_button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Toast弹出
            case R.id.test_show_window_button2:
                ++mToastType;
                switch (mToastType) {
                    case 0:
                        MyToast.showShort(this, "Toast短时间弹出");
                        break;
                    case 1:
                        MyToast.showShort(this, "Toast长时间弹出");
                        mToastType = 0;
                        break;
                }
                break;
            //自定义Toast弹出
            case R.id.test_show_window_button3:
                //由于视图控件ID不可控因素存在。要想在自定义toast里面去改变视图控件内容，那么请使用者自行针对自己定义的视图和MyToast再次封装。
                View inflate = LayoutInflater.from(this).inflate(R.layout.test_show_window_diy_toast, null);
                //此处只是调用底部显示自定义toast的方法，其他方法请自行进入MyToast对象里面去查看，里面注释很齐全的。
                MyToast.putBottomDiyToastShort(this, inflate, 190, 0, 30);
                break;
        }
    }
}

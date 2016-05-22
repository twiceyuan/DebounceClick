package com.twiceyuan.debounceclick.library;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by twiceYuan on 5/22/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p>
 * 避免重复点击工具类
 */
public class DebounceClick {

    /**
     * 防止重复点击的静态方法，可以设置多少毫秒内不再接收新事件
     *
     * @param view     设置事件的 View
     * @param duration 冷却时间
     * @param listener 事件监听
     */
    public static void onClick(final View view, final long duration, final View.OnClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                    view.setEnabled(false);
                    coolDown(view, duration);
                }
            }
        });
    }

    /**
     * 默认三百毫秒不接受重复的点击事件
     *
     * @param view     设置点击事件的 view
     * @param listener 点击监听器
     */
    public static void onClick(View view, View.OnClickListener listener) {
        onClick(view, 300, listener);
    }

    /**
     * 点击十周按钮即失效，在特定事件后恢复，恢复时调用 event.done(); 来标记按钮重新可用
     *
     * @param view     设置点击事件的操作
     * @param callback 事件回调，同时回调出一个控制按钮何时恢复的 event 控制器。
     */
    public static void onClick(View view, final Callback callback) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                v.setEnabled(false);
                callback.call(v, new ClickEvent() {
                    @Override public void done() {
                        v.setEnabled(true);
                    }
                });
            }
        });
    }

    /**
     * 在主线程运行任务
     *
     * @param runnable 任务
     */
    private static void runOnUiThread(Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    /**
     * 设置 View 冷却时间
     *
     * @param view     设置冷却时间的 View
     * @param duration 冷却时间
     */
    private static void coolDown(final View view, long duration) {
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        view.setEnabled(true);
                    }
                });
            }
        }, duration);
    }
}

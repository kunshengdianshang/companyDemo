package com.example.mdmall.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

public class AutoPollRecyclerView extends RecyclerView {
    private static final long delayTime= 50;//间隔多少时间后执行滚动
    AutoPollTask autoPollTask;//滚动线程
    private boolean running; //是否正在滚动
    private boolean canRun;//是否可以自动滚动，根据数据是否超出屏幕来决定

    public AutoPollRecyclerView(Context context) {
        super(context);
    }

    public AutoPollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        autoPollTask = new AutoPollTask(this);//实例化滚动刷新线程
    }

    static class AutoPollTask implements Runnable {
        private final WeakReference<AutoPollRecyclerView> mReference;

        //使用弱引用持有外部类引用 防止内存泄漏
        public AutoPollTask(AutoPollRecyclerView reference) {
            this.mReference = new WeakReference<AutoPollRecyclerView>(reference);
        }

        @Override
        public void run() {
            AutoPollRecyclerView recyclerView = mReference.get();//获取recyclerview对象
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2);//注意scrollBy和scrollTo的区别
                //延迟发送
                recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.delayTime);
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop();
        canRun = true;
        running = true;
        postDelayed(autoPollTask, delayTime);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

}

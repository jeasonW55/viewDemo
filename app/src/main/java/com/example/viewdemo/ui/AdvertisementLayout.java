package com.example.viewdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.manager.TaskManager;

/**
 * 广告类
 */
public class AdvertisementLayout extends FrameLayout {

    public static final long SECOND = 1000l;
    public static final int SHOW_ADS = 0x00000001;
    public static final int END_ADS = 0x00000002;

    private static final int[] mAdsArrays = {
            R.drawable.nvpushaonv,
            R.drawable.chuyinweilai,
            R.drawable.haitanshaonv,
            R.drawable.yongzhuangshaonv,
            R.drawable.jianhunshaonv,
            R.drawable.chengsanshaonv
            };

    private TextView mTvShowAdsTimer;

    private ImageView mIvShowAds;

    private Runnable mAdsRunnable = new Runnable() {

        String leftTime = "关闭|%sS";

        @Override
        public void run() {
            if (mDuration >= 0) {
                showAds(mDuration);
                leftTime = "关闭|" + mDuration + "s";
                Log.d("wangjishun", leftTime);
                mTvShowAdsTimer.setText(leftTime);
                --mDuration;
                postDelayed(this,  SECOND);
            } else {
                endAds();
            }
        }
    };


    /**
     * 广告时间
     */
    private int mDuration = 5;

    public AdvertisementLayout(Context context) {
        super(context);
    }

    public AdvertisementLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdvertisementLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        Log.d("wangjishun", "ad initView");
        mTvShowAdsTimer = findViewById(R.id.tv_show_ads_timer);
        mIvShowAds = findViewById(R.id.iv_show_ads);
        post(mAdsRunnable);
        mTvShowAdsTimer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                endAds();
            }
        });
    }

    private void endAds() {
        Context context = getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Layout layout = activity.getClass().getAnnotation(Layout.class);
            if (layout != null) {
                activity.setContentView(layout.layout());
            }
        }
        setVisibility(View.GONE);
        TaskManager.SingleTon.getInstance().sendMessage(END_ADS, null);
        TaskManager.SingleTon.getInstance().removeCallback(mAdsRunnable);
    }

    /**
     * 获取广告时间
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * 设置广告时间
     */
    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    private void showAds(int duration) {
        if (mIvShowAds != null && duration > -1 && duration < mAdsArrays.length) {
            mIvShowAds.setImageResource(mAdsArrays[duration]);
        }
    }
}

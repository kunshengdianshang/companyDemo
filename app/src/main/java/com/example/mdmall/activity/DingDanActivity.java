package com.example.mdmall.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.PagerAdapter;
import com.example.mdmall.fragment.AllDingDanFragment;
import com.example.mdmall.fragment.WaitPayFragment;
import com.example.mdmall.fragment.WaitPingJiaFragment;
import com.example.mdmall.fragment.WaitReceiveFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DingDanActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private AllDingDanFragment allDingDanFragment;
    private WaitPayFragment waitPayFragment;
    private WaitReceiveFragment waitReceiveFragment;
    private WaitPingJiaFragment waitPingJiaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        ButterKnife.bind(this);
        initTabViewpager();
    }
    private PagerAdapter mPagerAdapter;
    private String[] titles = {"全部","代付款","待收货","待评价"};
    private List<Fragment> fragments;
    private void initTabViewpager() {
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        allDingDanFragment = new AllDingDanFragment();
        waitPayFragment = new WaitPayFragment();
        waitReceiveFragment = new WaitReceiveFragment();
        waitPingJiaFragment = new WaitPingJiaFragment();
        fragments.add(allDingDanFragment);
        fragments.add(waitPayFragment);
        fragments.add(waitReceiveFragment);
        fragments.add(waitPingJiaFragment);

        mPagerAdapter.setTitles(titles);
        mPagerAdapter.setFragments(fragments);
        viewpager.setAdapter(mPagerAdapter);
        //将TabLayout和ViewPager绑定
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);

        viewpager.setOffscreenPageLimit(3);//设置ViewPage缓存界面数
        //mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}

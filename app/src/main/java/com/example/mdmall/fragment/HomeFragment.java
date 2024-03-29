package com.example.mdmall.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.activity.PositioningActivity;
import com.example.mdmall.activity.SearchActivity;
import com.example.mdmall.adapter.NsGvHandPickRecommendAdapter;
import com.example.mdmall.adapter.RvFunctionBallAdapter;
import com.example.mdmall.view.NonScrollGridView;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    public static final int REQUEST_CODE = 111;
    private static HomeFragment homeFragment;
    private View homeView;
    @BindView(R.id.rv_function_ball)
    RecyclerView rvFunctionBall;
    @BindView(R.id.ns_gv_recommend)
    NonScrollGridView nsGvRecommend;
    private NsGvHandPickRecommendAdapter nsGvAdapter;
    private RvFunctionBallAdapter rvFunctionBallAdapter;
    public static HomeFragment getInstance() {
        if (homeFragment == null) homeFragment = new HomeFragment();
        return homeFragment;
    }

    public HomeFragment() {
    }
    @OnClick(R.id.search_linearlayout)
    public void search_linearlayout_Click(){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (homeView == null) homeView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, homeView);
       // EventBus.getDefault().register(this);
        InitFunctionBall();
        InitRecommended();
        return homeView;
    }
    @OnClick({R.id.scanning,R.id.home_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scanning:
                requestCemera();

                /*Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);*/
                break;
            case R.id.home_set:
                startActivity(new Intent(getActivity(), PositioningActivity.class));
                break;
        }
    }
    //权限
    private void  requestCemera(){
        PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                Toast.makeText(getContext(), "访问摄像头", Toast.LENGTH_LONG).show();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                Toast.makeText(getContext(), "用户拒绝了访问摄像头", Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.CAMERA);
    }
    private void InitFunctionBall() {
        List<String> bannerBallRowsBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bannerBallRowsBeans.add("服饰");
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        rvFunctionBall.setLayoutManager(gridLayoutManager);
        rvFunctionBallAdapter = new RvFunctionBallAdapter(getActivity(), bannerBallRowsBeans);
        rvFunctionBall.setAdapter(rvFunctionBallAdapter);
    }
    private void InitRecommended() {
        List<String> mRecommends = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mRecommends.add("12345678987654321");
        }
        nsGvAdapter = new NsGvHandPickRecommendAdapter(mRecommends, getActivity());
        nsGvRecommend.setAdapter(nsGvAdapter);
    }
}

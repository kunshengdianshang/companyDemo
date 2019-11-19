package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.RvFunctionBallAdapter;
import com.example.mdmall.adapter.StoreAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreActivity extends BaseActivity {
    @BindView(R.id.store_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.store_guan)
    TextView store_guan;
    @BindView(R.id.store_linear)
    LinearLayout store_linear;
    @BindView(R.id.store_box)
    CheckBox store_box;
    private StoreAdapter storeAdapter;
    private int a=0;
    private int b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        getStoreAdapter("1");
    }
    @OnClick({R.id.store_guan,R.id.store_return,R.id.store_box,R.id.store_seach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_guan:
                if (a==0){
                    store_linear.setVisibility(View.VISIBLE);
                    store_guan.setText("完成");
                    getStoreAdapter("2");
                    a=1;
                }else {
                    store_linear.setVisibility(View.GONE);
                    store_guan.setText("管理");
                    getStoreAdapter("1");
                    store_box.setChecked(false);
                    a=0;
                }
                break;
            case R.id.store_return:
                finish();
                break;
            case R.id.store_box:
                if (b==0){
                    store_box.setChecked(true);
                    getStoreAdapter("3");
                    b=1;
                }else {
                    store_box.setChecked(false);
                    getStoreAdapter("4");
                    b=0;
                }
                break;
            case R.id.store_seach:
                startActivity(new Intent(StoreActivity.this,SearchActivity.class));
                break;
        }
    }
    private void getStoreAdapter(String a) {
        List<String> bannerBallRowsBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bannerBallRowsBeans.add("今锦上生鲜自营旗舰店");
        }
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutmanager);
        storeAdapter = new StoreAdapter(StoreActivity.this, bannerBallRowsBeans,a);
        recyclerView.setAdapter(storeAdapter);
    }
}

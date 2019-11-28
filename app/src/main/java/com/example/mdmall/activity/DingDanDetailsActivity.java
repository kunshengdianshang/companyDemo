package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.DingDanDetailsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DingDanDetailsActivity extends BaseActivity {


    @BindView(R.id.recy)
    RecyclerView recy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_details);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        DingDanDetailsAdapter dingDanDetailsAdapter = new DingDanDetailsAdapter(this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(dingDanDetailsAdapter);
    }
}

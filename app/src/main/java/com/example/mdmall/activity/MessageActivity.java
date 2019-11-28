package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.MessageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {


    @BindView(R.id.recy)
    RecyclerView recy;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        messageAdapter = new MessageAdapter(this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(messageAdapter);
    }
}

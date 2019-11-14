package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.SearchedAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.afl_cotent)
    AutoFlowLayout afl_cotent;

    @BindView(R.id.afl_cotent_hot)
    AutoFlowLayout afl_cotent_hot;

    private List<String> alist;
    private SearchedAdapter searchedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //测试数据
        initList();
        afl_cotent.setAdapter(new FlowAdapter(alist) {
            @Override
            public View getView(int position) {
                View item = View.inflate(SearchActivity.this,R.layout.special_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.text);
                tvAttrTag.setText(alist.get(position)+"");
                return item;
            }
        });

        afl_cotent_hot.setAdapter(new FlowAdapter(alist) {
            @Override
            public View getView(int position) {
                View item = View.inflate(SearchActivity.this,R.layout.special_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.text);
                tvAttrTag.setText(alist.get(position)+"");
                return item;
            }
        });

        initAdapter();

    }
    @BindView(R.id.recy)
    RecyclerView recy;
    private void initAdapter() {
        searchedAdapter = new SearchedAdapter(this);
        searchedAdapter.setData(alist);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(searchedAdapter);
    }

    private void initList() {
        alist=new ArrayList<>();
        alist.add("笔记本");
        alist.add("手机");
        alist.add("仙人掌");
        alist.add("李白");
        alist.add("沙雕");
        alist.add("宫本武藏");
        alist.add("西门飞雪");
        alist.add("司马懿");
        alist.add("诸葛亮");
    }
}

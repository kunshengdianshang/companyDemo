package com.example.mdmall.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.bumptech.glide.Glide;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.fragment.FilterFragment;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.drawer_content)
    FrameLayout drawer_content;
    @BindView(R.id.product_view1)
    View product_view1;
    @BindView(R.id.product_view2)
    View product_view2;
    @BindView(R.id.product_view3)
    View product_view3;
    @BindView(R.id.product_view4)
    View product_view4;
    private boolean refreshFlag = false;
    private int pageNum = 1;
    private RecyAdapter recyAdapter;
    private String name;
    boolean checkPrice = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        initXRecyclerView();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        search.setText(name+"");
        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("bundle", "");
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
    }
    public static void open(Context context, String name) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
    @OnClick({R.id.iv_top_back,R.id.filter,R.id.sort,R.id.volume,R.id.price,R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back://返回
                finish();
                break;
            case R.id.filter://侧拉
                drawerLayout.openDrawer(drawer_content);
                checkPrice = true;
                product_view1.setVisibility(View.GONE);
                product_view2.setVisibility(View.GONE);
                product_view3.setVisibility(View.GONE);
                product_view4.setVisibility(View.VISIBLE);
                break;
            case R.id.sort://综合
                product_view1.setVisibility(View.VISIBLE);
                product_view2.setVisibility(View.GONE);
                product_view3.setVisibility(View.GONE);
                product_view4.setVisibility(View.GONE);
                break;
            case R.id.volume://销量
                product_view1.setVisibility(View.GONE);
                product_view2.setVisibility(View.VISIBLE);
                product_view3.setVisibility(View.GONE);
                product_view4.setVisibility(View.GONE);
                break;
            case R.id.price://价格
                product_view1.setVisibility(View.GONE);
                product_view2.setVisibility(View.GONE);
                product_view3.setVisibility(View.VISIBLE);
                product_view4.setVisibility(View.GONE);
                break;
            case R.id.search://搜索
                startActivity(new Intent(ProductListActivity.this,SearchActivity.class));
                break;
        }
    }
    @SuppressLint("WrongConstant")
    private void initXRecyclerView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("蓝雪 冷冻大西洋红鱼柳 400g 3片 袋装 海 鲜水产");
        }
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreEnabled(true);
        recyAdapter = new RecyAdapter(ProductListActivity.this, list);
        recyclerView.setAdapter(recyAdapter);
        recyAdapter.notifyDataSetChanged();
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Log.d("hee_hee", "onRefresh: ");
                refreshFlag = true;
                pageNum = 1;
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Log.d("hee_hee", "onLoadMore: ");
                pageNum += 1;
                recyclerView.loadMoreComplete();
            }
        });
    }


    //适配器
    static class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.BeautyViewHolder> {
        private Context context;
        private List<String> list;

        public RecyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public BeautyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
            return new BeautyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final BeautyViewHolder holder, final int i) {
            holder.name.setText(list.get(i)+"");
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsActivity.open(context,"1");
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class BeautyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.name)
            TextView name;
            @BindView(R.id.linearLayout)
            LinearLayout linearLayout;

            public BeautyViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }
}

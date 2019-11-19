package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.CollectedAdapter;
import com.example.mdmall.bean.ShopCarBean;
import com.example.mdmall.interfaces.ShopCarInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectedActivity extends BaseActivity implements ShopCarInterface {

    @BindView(R.id.rela_bottom)
    RelativeLayout rela_bottom;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.text_guanli)
    TextView text_guanli;
    @OnClick(R.id.text_guanli)
    public void text_guanli_Click(){
        String s = text_guanli.getText().toString();
        if(s.equals("管理")){
            text_guanli.setText("完成");
            rela_bottom.setVisibility(View.VISIBLE);
            collectedAdapter.setSelectStatus(0);
            collectedAdapter.notifyDataSetChanged();
        }else{
            text_guanli.setText("管理");
            rela_bottom.setVisibility(View.GONE);
            collectedAdapter.setSelectStatus(1);
            collectedAdapter.notifyDataSetChanged();
        }
    }
    private CollectedAdapter collectedAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collected);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        if(collectedAdapter==null){
            collectedAdapter=new CollectedAdapter(this);
        }

        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(collectedAdapter);
    }

    @Override
    public void setShopCarAllImageChecked(boolean isSele) {

    }

    @Override
    public void isSelectIfShop(boolean isSele, int positon, int childPosition, List<ShopCarBean.ShopChildBean> li) {

    }

}

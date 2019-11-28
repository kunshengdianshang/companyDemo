package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.RvFunctionBallAdapter;
import com.example.mdmall.adapter.SecuritiesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity {
    @BindView(R.id.details_securities)
    RecyclerView details_securities;
    @BindView(R.id.details_evaluation)
    AutoFlowLayout details_evaluation;
    @BindView(R.id.details_original)
    TextView details_original;
    private SecuritiesAdapter securitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        getSecurities();
        getEvaluation();
        details_original.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
    }
    public static void open(Context context, String goods_id) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("goods_id", goods_id);
        context.startActivity(intent);
    }
    private void getSecurities(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("满99减30");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        details_securities.setLayoutManager(layoutManager);
        securitiesAdapter = new SecuritiesAdapter(DetailsActivity.this, list);
        details_securities.setAdapter(securitiesAdapter);
    }
    private void getEvaluation(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("有图(81)");
        }
        details_evaluation.setAdapter(new FlowAdapter(list) {
            @Override
            public View getView(int position) {
                View item = View.inflate(DetailsActivity.this,R.layout.details_evaluation_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.text);
                tvAttrTag.setText(list.get(position)+"");
                return item;
            }
        });
    }
}

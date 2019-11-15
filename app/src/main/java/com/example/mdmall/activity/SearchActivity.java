package com.example.mdmall.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.SearchedAdapter;
import com.example.mdmall.apps.MyApp;
import com.example.mdmall.datastock.DaoSession;
import com.example.mdmall.datastock.SearchStockBean;
import com.example.mdmall.datastock.SearchStockBeanDao;

import java.lang.reflect.Array;
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
    private List<SearchStockBean> searchStockBeans;
    private SearchStockBeanDao searchStockBeanDao;
    private SearchStockBean searchStockBean;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //测试数据
        //initList();
        //数据库
        initGreenDao();
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
        listenerEdit();
    }

    private boolean isSearch;
    private static final String TAG="SearchActivity-----";
    private void listenerEdit() {
        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    addSearchData();
                    initGreenDao();
                    afl_cotent.clearViews();
                    afl_cotent.setAdapter(new FlowAdapter(alist) {
                        @Override
                        public View getView(int position) {
                            View item = View.inflate(SearchActivity.this,R.layout.special_item, null);
                            TextView tvAttrTag = (TextView) item.findViewById(R.id.text);
                            tvAttrTag.setText(alist.get(position)+"");
                            return item;
                        }
                    });
                    InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(edit_search.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    Log.d(TAG,"搜索");
                    return true;
                }
                return false;
            }
        });

        edit_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

    }

    private void initGreenDao() {
        if(daoSession==null){
            alist=new ArrayList<>();
            daoSession = MyApp.getmDaoSession();
            searchStockBeanDao = daoSession.getSearchStockBeanDao();
        }
        //searchStockBeanDao.deleteAll();
        searchStockBeans = searchStockBeanDao.loadAll();
        alist.clear();
        for(int i = 0 ; i < searchStockBeans.size();i++){
            Log.d(TAG,"id="+searchStockBeans.get(i).getId());
            alist.add(""+searchStockBeans.get(i).getStr());
        }
    }
    @BindView(R.id.edit_search)
    EditText edit_search;
    public void addSearchData(){
        String s = edit_search.getText().toString();
        if(s.equals("")||s.isEmpty()){
            return;
        }
        searchStockBean = new SearchStockBean();

        for(int i = 0;i < searchStockBeanDao.loadAll().size();i++){
            if(searchStockBeans.get(i).getStr().equals(s)){
                searchStockBeanDao.delete(searchStockBeanDao.loadAll().get(i));
                break;
            }
        }
        if(searchStockBeanDao.loadAll().size()>9){
            searchStockBeanDao.delete(searchStockBeanDao.loadAll().get(searchStockBeanDao.loadAll().size()-1));
        }
        searchStockBean.setStr(s);
        searchStockBeanDao.insert(searchStockBean);
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

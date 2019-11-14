package com.example.mdmall.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mdmall.BaseFragment;
import com.example.mdmall.R;
import com.example.mdmall.adapter.SecondaryGridAdapter;
import com.example.mdmall.view.NonScrollGridView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyFragment extends BaseFragment {
    private static ClassifyFragment classifyFragment;
    private View view;
    @BindView(R.id.first_listView)
    ListView first_listView;
    @BindView(R.id.secondary_listView)
    ListView secondary_listView;
    private List<String> list;
    private ClassifyAdapter classifyAdapter;
    private SecondaryAdapter secondaryAdapter;
    private ImageView image;

    public static ClassifyFragment getInstance() {
        if (classifyFragment == null) classifyFragment = new ClassifyFragment();
        return classifyFragment;
    }

    public ClassifyFragment() {
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
       // EventBus.getDefault().register(this);
        InitExt();
        View header = getLayoutInflater().inflate(R.layout.twocategory_top, null);
        secondary_listView.addHeaderView(header);
        image = header.findViewById(R.id.image);
        //点击切换事件
        first_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classifyAdapter.setSelectedPosition(position);
                classifyAdapter.notifyDataSetChanged();
                /*secondaryAdapter = new SecondaryAdapter(list.get(position).getChildren(),getContext());
                secondary_listView.setAdapter(secondaryAdapter);*/
            }
        });
        return view;
    }
    private void InitExt() {
        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        classifyAdapter = new ClassifyAdapter(getActivity(), list);
        first_listView.setAdapter(classifyAdapter);
        secondaryAdapter = new SecondaryAdapter(getActivity(), list);
        secondary_listView.setAdapter(secondaryAdapter);
    }
    //分类商品一级分类列表
    class ClassifyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<String> data;
        private int pos;

        public ClassifyAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FirstHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.classify_first_item, null);
                holder = new FirstHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (FirstHolder) convertView.getTag();
            }

            if (pos == position) {
                holder.first_item_text.setText(list.get(position));
                holder.first_item_text.setTextColor(0xFFFFCF31);
                holder.first_item_view.setVisibility(View.VISIBLE);
                TextPaint tp = holder.first_item_text.getPaint();
                holder.first_item_linear.setBackgroundColor(0xFFF0F0F0);
                tp.setFakeBoldText(true);
            } else {
                holder.first_item_text.setText(list.get(position));
                holder.first_item_text.setTextColor(0xFF252525);
                holder.first_item_view.setVisibility(View.GONE);
                TextPaint tp = holder.first_item_text.getPaint();
                holder.first_item_linear.setBackgroundColor(0xFFFFFFFF);
                tp.setFakeBoldText(false);
            }
            /*secondaryAdapter = new SecondaryAdapter(list.get(0).getChildren(),getContext());
            secondary_listView.setAdapter(secondaryAdapter);*/
            return convertView;
        }

        class FirstHolder {
            @BindView(R.id.first_item_text)
            TextView first_item_text;
            @BindView(R.id.first_item_view)
            View first_item_view;
            @BindView(R.id.first_item_linear)
            LinearLayout first_item_linear;
            FirstHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        public void setSelectedPosition(int pos) {
            this.pos = pos;
        }
    }
    //分类商品二级分类列表
    class SecondaryAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<String> data;

        public SecondaryAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FirstHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.classify_secondry_item, null);
                holder = new FirstHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (FirstHolder) convertView.getTag();
            }
            holder.secondry_item_text.setText(data.get(position));
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                strings.add("abc");
            }
            SecondaryGridAdapter secondaryGridAdapter = new SecondaryGridAdapter(strings, context);
            holder.secondary_gridView.setAdapter(secondaryGridAdapter);
            return convertView;
        }

        class FirstHolder {
            @BindView(R.id.secondry_item_text)
            TextView secondry_item_text;
            @BindView(R.id.secondary_gridView)
            NonScrollGridView secondary_gridView;
            FirstHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}

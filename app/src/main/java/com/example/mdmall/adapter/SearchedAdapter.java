package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;
import com.example.mdmall.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchedAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;
    public SearchedAdapter(Context context) {
        this.context=context;
        list=new ArrayList<>();
    }
    public void setData(List<String> list){
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchedViewHolder(View.inflate(context,R.layout.searched_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           SearchedViewHolder svh = (SearchedViewHolder) holder;
           svh.searched_text.setText(list.get(position)+"");
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        if(list.size()==0){
            return 0;
        }
        return list.size();
    }
    public class SearchedViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.searched_text)
        TextView searched_text;

        public SearchedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

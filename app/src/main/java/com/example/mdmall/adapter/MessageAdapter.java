package com.example.mdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter {
    private Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(View.inflate(context,R.layout.message_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           MessageViewHolder mvh= (MessageViewHolder) holder;
           if(position==9){
               mvh.view_message.setVisibility(View.GONE);
           }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class MessageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.view_message)
        View view_message;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

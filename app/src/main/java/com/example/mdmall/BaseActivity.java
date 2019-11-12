package com.example.mdmall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog pd;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void showProgress(String msg){
        pd = new ProgressDialog(this);
        if(!TextUtils.isEmpty(msg)){
            pd.setMessage(msg);
        }
        pd.show();
    }

    public void dismissProgress(){
        if(pd!=null){
            pd.dismiss();
        }
    }
}

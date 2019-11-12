package com.example.mdmall.network;

import android.app.Activity;

import java.util.ArrayList;

public class JavascriptInterface {
    private Activity context;
    private final ArrayList<String> images;

    public JavascriptInterface(Activity context) {
        this.context = context;
        images = new ArrayList<>();
    }

//    @android.webkit.JavascriptInterface
//    public void openImage(String img) {
//        if (images!=null)images.clear();
//        images.add(img);
//        Log.d("img", "openImage: img="+img);
//        Intent intent = new Intent(context,ViewPagerImageActivity.class);
//        intent.putExtra("image", img);
//        intent.putStringArrayListExtra("url", images);
//        context.startActivity(intent);
//    }
}



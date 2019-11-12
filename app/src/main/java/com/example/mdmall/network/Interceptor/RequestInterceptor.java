package com.example.mdmall.network.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 如果用户已经登录，自动添加uid和token
 * Created by staffy on 2016/2/24.
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
//        String reqTime = System.currentTimeMillis() + "";
//        Logger.d("reqTime is " + reqTime);
//        String sign = MD5Util.getMD5String(reqTime + ConstantValue.SECRECTKEY);
//        String token = PreferencesUtils.getPreferences(AppController.getInstance().getApplicationContext(), PreferencesUtils.LOCAL_TOKEN);
//        if(token == null){
//            token = "";
//        }
//        Logger.d("sign is " + sign + ",token is " + token);
        Request original = chain.request();
        Request.Builder requestBuilder;
//        if(original.body() instanceof MultipartBody){
//            requestBuilder = original.newBuilder()
//                    .header("reqTime", reqTime)
//                    .header("accessKey", "APP-ANDROID")
//                    .header("sign", sign)
//                    .header("Content-Type", "multipart/form-data")
//                    .header("jwt",token)
//                    .method(original.method(), original.body());
//        }else{
            requestBuilder = original.newBuilder()
                    .header("device", "APP-ANDROID")
//                    .header("accessKey", "APP-ANDROID")
//                    .header("sign", sign)
                    .header("Content-Type", "application/x-www-form-urlencoded")
//                    .header("jwt",token)
                    .method(original.method(), original.body());

//        }

        Request request = requestBuilder.build();
        return chain.proceed(request);

    }


}

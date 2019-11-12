/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.mdmall.network;

import com.example.mdmall.XynApi;
import com.example.mdmall.bean.MyResult;
import com.example.mdmall.network.Interceptor.RequestInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by staffy on 24/2/16.
 */
public class MyRetrofit {

    public static final String BASE_HTTP_URL = "http://tlhcs.chinachch.net/";
    public static final String BASE_URL = BASE_HTTP_URL+"v6/";
    final XynApi mXynService;

//    final static Gson gson = new GsonBuilder()
////            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            .serializeNulls()
//            .create();

    MyRetrofit() {

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        //日志
//        if(ConstantValue.DEBUG){
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }else{
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }


//        final DigestAuthenticator authenticator = new DigestAuthenticator(
//                new Credentials(Constants.USERNAME, Constants.PASSWORD));
//        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                String sessionid = PreferencesUtils.getPreferences(AppController.getInstance(),PreferencesUtils.SESSONID);
//                if(!TextUtils.isEmpty(sessionid)){
//                    Request newRequest = chain.request().newBuilder().addHeader("Cookie", "s="+sessionid+";from=yndh_app;").build();
//                    Logger.d("session id is "+sessionid);
//                    return chain.proceed(newRequest);
//                }
//                return chain.proceed(chain.request());
//            }
//        };

        RequestInterceptor requestInterceptor = new RequestInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
//                .addInterceptor(requestInterceptor)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

//        client.setReadTimeout(12, TimeUnit.SECONDS);
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(MyResult.class, new ResultJsonDeser())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        mXynService = retrofit.create(XynApi.class);
    }

    public XynApi geXynService() {
        return mXynService;
    }
}

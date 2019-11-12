package com.example.mdmall.network;

import com.example.mdmall.bean.MyResult;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class ResultJsonDeser implements JsonDeserializer<MyResult<?>> {

    @Override
    public MyResult<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        MyResult response = new MyResult();
        if (json.isJsonObject()){
            JsonObject jsonObject = json.getAsJsonObject();
            String  state = jsonObject.get("state").getAsString();
            response.setState(state);
            response.setMsg(jsonObject.get("msg").getAsString());
            if (!state.equals("1")){
                return response;
            }
            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            response.setData(context.deserialize(jsonObject.get("data"), itemType));
            return response;
        }
        return response;
    }
}

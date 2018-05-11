package com.lsq.community.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil{
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json,Class<T> clazz){
       T obj = gson.fromJson(json, clazz);
       return obj;
    }
}

package ru.germes.plus.site.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class Util {

    public static Map<String, String> getFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
    }
}

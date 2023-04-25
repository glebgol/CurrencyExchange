package utils.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.IJsonMapper;

public class JsonMapper implements IJsonMapper {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String map(Object object) {
        return gson.toJson(object);
    }
}

package labs.common;

import com.google.gson.Gson;

public class MessageTransformer {

    public String transformIntoGson(Message message){
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    public Message transformFromGson(String message){
        Gson gson = new Gson();
        return gson.fromJson(message, Message.class);
    }
}

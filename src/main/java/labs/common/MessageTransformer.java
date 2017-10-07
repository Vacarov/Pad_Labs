package labs.common;

import com.google.gson.Gson;

public class MessageTransformer {

    public String transformIntoGson(Message message){
        Gson gson = new Gson();

//        Gson json2 = new Gson();
//        Message m = gson.fromJson(gson.toJson(message), Message.class);
//        System.out.println(m);
        System.out.println(gson.toJson(message));

        return gson.toJson(message);
    }

    public Message transformFromGson(String input){
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(input), Message.class);
    }
}

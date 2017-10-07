package labs;

import labs.common.Message;
import labs.common.MessageLocation;
import labs.common.MessageTransformer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainForTraining {
    public static void main(String[] args) {
        String mesaj = "asfsdg";
        MessageTransformer messageTransformer = new MessageTransformer();
        messageTransformer.transformIntoGson(new Message(MessageLocation.SENDER));
        System.out.println(messageTransformer.toString());
    }
}

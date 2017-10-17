package labs;

import labs.common.Message;
import labs.common.MessageOrder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainForTraining {
    public static void main(String[] args) throws Exception {
//        String mesaj = "asfsdg";
//        MessageTransformer messageTransformer = new MessageTransformer();
//        messageTransformer.transformIntoGson(new Message(MessageOrder.SEND));
//        System.out.println(messageTransformer.toString());
        Queue<Message> queue = new ConcurrentLinkedQueue<Message>();

        Writer writer = new FileWriter("Output.json");
        Message message = new Message("ananas", MessageOrder.SEND);
        Message message2 = new Message("ananas111110000000000000", MessageOrder.SEND);

        queue.add(message);
        queue.add(message2);
    }
}

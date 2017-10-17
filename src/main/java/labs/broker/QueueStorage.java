package labs.broker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import labs.common.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueStorage {

    private String fileName = "QueueStorage.json";
    private String filePath = "/home/vvacarov/IdeaProjects/pad/";

    public void storeQueueWhenApplicationStopped(final ConcurrentLinkedQueue<Message> queue) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(new File(filePath + fileName), queue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public final ConcurrentLinkedQueue<Message> restoreQueue() {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messageList = new ArrayList<Message>();
        Queue<Message> queue = new ConcurrentLinkedQueue<Message>();
        try {
            messageList = mapper.readValue(new File(filePath + fileName), new TypeReference<List<Message>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        queue.addAll(messageList);
        return (ConcurrentLinkedQueue<Message>) queue;
    }

    public boolean isEmptyQueue() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            if (br.readLine() == null)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

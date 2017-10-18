package labs.broker;

import labs.common.Message;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Broker extends Thread {
    private ServerSocket serverSocket;

    public Broker(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        ConcurrentLinkedQueue<Message> queue = null;
        QueueStorage queueStorage = new QueueStorage();

        if (!queueStorage.isEmptyQueue()) {
            queue = queueStorage.restoreQueue();
            System.out.println("Queue is restored:\n " + queue.toString());
        } else queue = new ConcurrentLinkedQueue<>();

        queueStorage.storeQueueWhenApplicationStopped(queue);
        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                final ConcurrentLinkedQueue<Message> finalQueue = queue;
                new Thread(() -> {
                    new ClientConnection(clientSocket).start(finalQueue);
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
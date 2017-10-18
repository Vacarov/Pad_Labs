package labs.broker;

import labs.common.Message;
import labs.common.MessageOrder;
import labs.common.MessageTransformer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
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
        Queue<Message> queue = null;
        QueueStorage queueStorage = new QueueStorage();

        if (!queueStorage.isEmptyQueue()) {
            queue = queueStorage.restoreQueue();
            System.out.println("Queue is restored:\n " + queue.toString());
        } else queue = new ConcurrentLinkedQueue<>();

        queueStorage.storeQueueWhenApplicationStopped((ConcurrentLinkedQueue<Message>) queue);
        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ConcurrentLinkedQueue<Message> finalQueue = (ConcurrentLinkedQueue<Message>) queue;
                new Thread(() -> {
//                    ClientConnection clientConnection = new ClientConnection(clientSocket).start(finalQueue);
                                    }).start();
//                ClientConnection clientConnection = new ClientConnection(clientSocket);
//                clientConnection.start((ConcurrentLinkedQueue<Message>) queue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
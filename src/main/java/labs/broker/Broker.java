package labs.broker;

import labs.common.Message;
import labs.common.MessageOrder;
import labs.common.MessageTransformer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        final Queue<Message> queue = new ConcurrentLinkedQueue<Message>();

        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Hello client with port " + clientSocket.getPort());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String input;

                while ((input = in.readLine()) != null) {
                    MessageTransformer messageTransformer = new MessageTransformer();
                    Message message = messageTransformer.transformFromGson(input);
                    if (message.getOrder() == MessageOrder.SEND) {
                        queue.add(message);
                    } else {
                        if (queue.isEmpty()) {
                            System.out.println("No more messages in queue");
                            break;
                        } else {
                            message = queue.poll();
                            final String json = messageTransformer.transformIntoGson(message);
                            out.println(json);
                        }
                    }
                    System.out.println(queue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
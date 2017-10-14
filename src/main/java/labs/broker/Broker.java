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

public class Broker {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Queue<Message> queue;

    public void start(int port) throws Exception {
        queue = new ConcurrentLinkedQueue<Message>();
        serverSocket = new ServerSocket(port);
        System.out.println("I am waiting for clients...");
        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Hello form client port " + clientSocket.getPort());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input;

            while ((input = in.readLine()) != null) {
                MessageTransformer messageTransformer = new MessageTransformer();
                Message message = messageTransformer.transformFromGson(input);
                if (message.getOrder() == MessageOrder.SEND) {
                    queue.add(message);
                } else{
                    queue.poll();
                }
                System.out.println(queue);
            }
        }
    }

    public void stop() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
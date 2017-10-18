package labs.broker;

import labs.common.Message;
import labs.common.MessageOrder;
import labs.common.MessageTransformer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientConnection extends Thread {
    private Socket clientSocket;

    public ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void start(ConcurrentLinkedQueue<Message> queue) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

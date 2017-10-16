package labs.sender;

import labs.common.Message;
import labs.common.MessageTransformer;

import java.io.PrintWriter;
import java.net.Socket;

public class Sender extends Thread {
    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        System.out.println("This is sender from port: " + clientSocket.getLocalPort());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendMessage(Message message) throws Exception {
        MessageTransformer messageTransformer = new MessageTransformer();
        final String json = messageTransformer.transformIntoGson(message);
        out.println(json);
    }

    public void stopConnection() throws Exception {
        out.close();
        clientSocket.close();
    }
}

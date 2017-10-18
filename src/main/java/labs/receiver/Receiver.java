package labs.receiver;

import labs.common.Message;
import labs.common.MessageOrder;
import labs.common.MessageTransformer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        System.out.println("This is receiver from port: " + clientSocket.getLocalPort());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendReceiveRequest() throws Exception {
        MessageTransformer messageTransformer = new MessageTransformer();
        final String json = messageTransformer.transformIntoGson(new Message("", MessageOrder.RECEIVE));
        out.println(json);
    }

    public void receiveAndShowMessage() throws Exception {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String input = in.readLine();
        MessageTransformer messageTransformer = new MessageTransformer();
        Message message = messageTransformer.transformFromGson(input);
        System.out.println(message.toString());
    }
}

package labs.broker;

import com.google.gson.Gson;
import labs.common.MessageTransformer;
import org.json.JSONObject;

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
    private Queue<String> queue;

    public void start(int port) throws Exception {
        queue = new ConcurrentLinkedQueue<String>();
        serverSocket = new ServerSocket(port);
        System.out.println("I am waiting for clients...");
        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Hello form client port " + clientSocket.getPort());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = in.readLine();

//            JSONObject jObject = new JSONObject(input);
//            String currentLocation = jObject.getString("id");
//            System.out.println(currentLocation);

            while (in.readLine() != null) {
                input = in.readLine();
                System.out.println(in.toString());
                MessageTransformer messageTransformer = new MessageTransformer();
                System.out.println(messageTransformer.transformFromGson(input).toString());

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
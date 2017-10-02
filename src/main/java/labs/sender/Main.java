package labs.sender;

public class Main {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        sender.startConnection("localhost", 4500);
        sender.sendMessage("hello from client");
        sender.stopConnection();
    }
}

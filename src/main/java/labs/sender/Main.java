package labs.sender;

import labs.common.Message;
import labs.common.MessageLocation;

public class Main {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        sender.startConnection("localhost", 4500);
        while (true) {
            Message message = new Message(MessageLocation.SENDER);
            sender.sendMessage(message);
        }
    }
}

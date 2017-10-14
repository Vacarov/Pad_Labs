package labs.sender;

import labs.common.Message;
import labs.common.MessageOrder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        sender.startConnection("localhost", 4500);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            Message message = new Message(text, MessageOrder.SEND);
            sender.sendMessage(message);
        }
    }
}

package labs.receiver;

public class Main {
    public static void main(String[] args) throws Exception {
        Receiver receiver = new Receiver();
        receiver.startConnection("localhost", 4500);
        receiver.sendReceiveRequest();
        while (true) {
            receiver.receiveAndShowMessage();
        }
    }
}

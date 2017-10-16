package labs.receiver;

public class Main {
    public static void main(String[] args) throws Exception {
        Receiver receiver = new Receiver();
        receiver.startConnection("localhost", 4500);
        while (true) {
            receiver.sendReceiveRequest();
            receiver.receiveAndShowMessage();
            Thread.sleep(5000);
        }
    }
}

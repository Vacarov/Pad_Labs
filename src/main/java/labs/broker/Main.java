package labs.broker;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Broker broker = new Broker(4500);
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

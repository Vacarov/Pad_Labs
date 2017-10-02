package labs.broker;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Broker broker = new Broker();
            broker.start(4500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package labs;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainForTraining {
    public static void main(String[] args) {
        Queue queue = new ConcurrentLinkedQueue();
        queue.add(20);
        queue.add(50);
        queue.add(80);
        queue.add(100);
        queue.add(80);
        queue.add(180);
        queue.remove();
        queue.remove();
        System.out.println(queue);
    }
}

package labs.broker;

import jdk.incubator.http.internal.common.Queue;

public class QueueStorage {

    public void storeQueueWhenApplicationStopped(final Queue queue) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println(queue);
            }
        }));
    }
}

package labs.common;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String text;
    private Date createdAt;
    private MessageLocation currentLocation;

    public Message(MessageLocation currentLocation) {
        this.id = count.incrementAndGet();
        Scanner scanner = new Scanner(System.in);
        this.text =  scanner.next();
        this.createdAt = new Date();
        this.currentLocation = currentLocation;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = new Date();
    }

    public MessageLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(MessageLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", currentLocation=" + currentLocation +
                '}';
    }
}

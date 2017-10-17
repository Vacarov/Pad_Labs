package labs.common;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String text;
    private Date createdAt;
    private MessageOrder order;

    public Message() {
    }

    public Message(String text, MessageOrder order) {
        this.id = count.incrementAndGet();
        this.text =  text;
        this.createdAt = new Date();
        this.order = order;
    }

    public Message(int id, String text, Date createdAt, MessageOrder order) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.order = order;
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

    public MessageOrder getOrder() {
        return order;
    }

    public void setOrder(MessageOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", order=" + order +
                "}";
    }
}

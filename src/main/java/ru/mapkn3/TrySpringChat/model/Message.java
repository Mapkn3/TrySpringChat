package ru.mapkn3.TrySpringChat.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements PrettyEntity {
    private Long id;
    @Size(max = 255, message = "Max length for message: 255")
    private String body;
    private Account from;
    private Account to;

    public Message() {
        this(0L, "");
    }

    public Message(Long id) {
        this(id, "");
    }

    public Message(String body) {
        this(0L, body);
    }

    public Message(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    @Override
    public Serializable primaryKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id +
                ", body='" + body + '\'' +
                ", from=" + from.toString() +
                ", to=" + to.toString() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            Message tmp = (Message) obj;
            return tmp.id.equals(this.id) && tmp.body.equals(this.body);
        } else {
            return false;
        }
    }
}

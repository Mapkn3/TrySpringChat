package ru.mapkn3.TrySpringChat.service;

import ru.mapkn3.TrySpringChat.model.Account;
import ru.mapkn3.TrySpringChat.model.Message;

import java.util.List;

public interface MessageService {
    Message getMessage(Long id);

    Long addNewMessage(Message message);

    List<Message> getAllMessages();

    List<Message> getHistory(Account a, Account b);

    Message updateMessage(Message message);

    void deleteMessage(Message message);
}

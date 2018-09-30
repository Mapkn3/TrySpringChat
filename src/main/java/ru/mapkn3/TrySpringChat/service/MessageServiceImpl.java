package ru.mapkn3.TrySpringChat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.TrySpringChat.dao.PrettyEntityDao;
import ru.mapkn3.TrySpringChat.model.Account;
import ru.mapkn3.TrySpringChat.model.Message;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
public class MessageServiceImpl implements MessageService {
    private final static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    private PrettyEntityDao messageDao;

    @Override
    @Transactional(readOnly = true)
    public Message getMessage(Long id) {
        logger.debug("Getting message with id = " + id);
        return messageDao.findById(Message.class, id);
    }

    @Override
    public Long addNewMessage(Message message) {
        Long id = (Long) messageDao.save(message);
        logger.debug("Id of new Message: " + id);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        List<Message> messages = messageDao.getAll(Message.class);
        logger.debug("Get " + messages.size() + " messages:");
        for (Message message : messages) {
            logger.debug(message.toString());
        }
        return messages;
    }

    @Override
    public List<Message> getHistory(Account a, Account b) {
        List<Message> messages = messageDao.getAll(Message.class);
        List<Message> history = messages.stream()
                .filter(m -> (m.getFrom().equals(a) && m.getTo().equals(b)) || (m.getFrom().equals(b) && m.getTo().equals(a)))
                .sorted(Comparator.comparing(Message::getId))
                .collect(Collectors.toList());
        logger.debug("Get " + history.size() + " messages:");
        for (Message message : history) {
            logger.debug(message.toString());
        }
        return history;
    }

    @Override
    public Message updateMessage(Message message) {
        Message oldMessage = messageDao.findById(Message.class, message.primaryKey());
        Message newMessage = messageDao.update(message);
        logger.debug("Old message: " + oldMessage.toString());
        logger.debug("New message: " + newMessage.toString());
        return newMessage;
    }

    @Override
    public void deleteMessage(Message message) {
        logger.debug("Delete message: " + message.toString());
        messageDao.delete(message);
    }
}

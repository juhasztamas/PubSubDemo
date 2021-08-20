package com.spring.messaging.PubSubDemo.service;

import com.spring.messaging.PubSubDemo.repository.MessageRepository;
import com.spring.messaging.PubSubDemo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(final Message message) {
        messageRepository.save(message);
    }

    public List<Message> getAll() {
        List<Message> result = new ArrayList<Message>();
        messageRepository.findAll().forEach(result::add);
        return result;
    }
}

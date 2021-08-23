package com.spring.messaging.PubSubDemo.service;

import com.spring.messaging.PubSubDemo.model.Payload;
import com.spring.messaging.PubSubDemo.repository.MessageRepository;
import com.spring.messaging.PubSubDemo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(final Note note) {
        messageRepository.save(note);
    }

    public List<Note> getAll() {
        List<Note> result = new ArrayList<Note>();
        messageRepository.findAll().forEach(result::add);
        return result;
    }
}

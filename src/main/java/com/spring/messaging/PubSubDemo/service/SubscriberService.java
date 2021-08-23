package com.spring.messaging.PubSubDemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.messaging.PubSubDemo.model.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SubscriberService implements MessageListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onMessage(final Message received, final byte[] bytes) {
        final var message = convertFrom(received);
        log.info("Received message [{}]", message);
        sendToWs(message);
        saveReceivedMessage(message);
    }

    private void sendToWs(final String message) {
        log.info("sending message to ws [{}]", message);
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
    }

    private String convertFrom(final Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    private void saveReceivedMessage(final String message) {
        try {
            final var note = mapper.readValue(message, Note.class);

            messageService.save(note);
        } catch (final Exception e) {
            log.error("could not parse the message", e);
        }
    }
}

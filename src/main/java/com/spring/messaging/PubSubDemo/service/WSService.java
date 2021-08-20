package com.spring.messaging.PubSubDemo.service;

import com.spring.messaging.PubSubDemo.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WSService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(final MessageDTO message) throws Exception {
        log.info("Sending message to WS [{}]", message);
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
    }
}
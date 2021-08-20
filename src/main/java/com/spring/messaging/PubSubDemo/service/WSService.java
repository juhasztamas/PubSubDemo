package com.spring.messaging.PubSubDemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WSService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(String message) throws Exception {
        log.info("Sending message to WS [{}]", message);
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
    }
}
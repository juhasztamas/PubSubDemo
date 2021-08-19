package com.spring.messaging.PubSubDemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SubscriberService implements MessageListener {

    @Override
    public void onMessage(final Message message, final byte[] bytes) {
        log.info("Received message [{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}

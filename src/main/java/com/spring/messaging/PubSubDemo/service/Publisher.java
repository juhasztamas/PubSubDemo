package com.spring.messaging.PubSubDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.messaging.PubSubDemo.exception.PublisherException;
import com.spring.messaging.PubSubDemo.model.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Publisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private ObjectMapper objectMapper;

    public void publish(final Payload message) {
        log.info("Publishing a message with content [{}]", message.getContent());
        try {
            final var note = objectMapper.writeValueAsString(message);
            redisTemplate.convertAndSend(topic.getTopic(), note);
        } catch (final Exception e) {
            log.error("Cannot publish message", e);
            throw new PublisherException(e.getMessage());
        }
    }
}

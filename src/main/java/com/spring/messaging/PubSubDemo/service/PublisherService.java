package com.spring.messaging.PubSubDemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.messaging.PubSubDemo.model.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private ObjectMapper objectMapper;

    public void publish(final Payload message) {
        log.info("Publishing a message with content [{}]", message.getContent());
        String toPublish = "";
        try {
            toPublish = objectMapper.writeValueAsString(message);
        } catch (final JsonProcessingException e) {
            log.error("cannot convert message", e);
        }
        redisTemplate.convertAndSend(topic.getTopic(), toPublish);
    }
}

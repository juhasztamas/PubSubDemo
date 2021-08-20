package com.spring.messaging.PubSubDemo.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private final StringRedisTemplate redisTemplate;

    private final ChannelTopic topic;

    public PublisherService(final StringRedisTemplate redisTemplate,
                            final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}

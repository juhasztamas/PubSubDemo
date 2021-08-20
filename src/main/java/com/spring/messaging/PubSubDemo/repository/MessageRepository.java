package com.spring.messaging.PubSubDemo.repository;

import com.spring.messaging.PubSubDemo.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}

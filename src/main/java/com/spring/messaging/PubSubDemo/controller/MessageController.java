package com.spring.messaging.PubSubDemo.controller;

import com.spring.messaging.PubSubDemo.model.Message;
import com.spring.messaging.PubSubDemo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity publish(@RequestBody Message message) {
        if (Objects.isNull(message.getContent())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        log.info("Publishing a message [{}]", message.getContent());
        messageService.save(message);
        return new ResponseEntity(message.getContent(), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        log.info("Retrieving all stored messages");
        final List<Message> result = messageService.getAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

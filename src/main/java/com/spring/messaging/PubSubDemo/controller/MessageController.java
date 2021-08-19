package com.spring.messaging.PubSubDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/messages")
public class MessageController {

    @PostMapping
    public void publish() {
        log.info("Publishing a message");
    }

    @GetMapping
    public void getAll() {
        log.info("Retrieving all stored messages");
    }
}

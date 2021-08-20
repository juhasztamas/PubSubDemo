package com.spring.messaging.PubSubDemo.controller;

import com.spring.messaging.PubSubDemo.dto.MessageDTO;
import com.spring.messaging.PubSubDemo.model.Message;
import com.spring.messaging.PubSubDemo.service.MessageService;
import com.spring.messaging.PubSubDemo.service.PublisherService;
import com.spring.messaging.PubSubDemo.service.WSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PublisherService publisherService;

   @Autowired
   private WSService wsService;

    @PostMapping
    public ResponseEntity publish(@Valid @RequestBody Message message) {
        log.info("Publishing a message [{}]", message.getContent());
        publisherService.publish(message.getContent());

        messageService.save(message);
        try {
            final var toSend = MessageDTO
                    .builder()
                    .content(message.getContent())
                    .timeStamp(message.getTimestamp())
                    .build();
            wsService.sendMessage(toSend);
        } catch (final Exception e) {
            log.error("Cannot send message to WS", e);
        }
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAll() {
        log.info("Retrieving all stored messages");
        final List<Message> result = messageService.getAll();
        return ResponseEntity.ok(result);
    }
}

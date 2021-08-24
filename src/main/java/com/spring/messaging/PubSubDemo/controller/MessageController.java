package com.spring.messaging.PubSubDemo.controller;

import com.spring.messaging.PubSubDemo.model.Note;
import com.spring.messaging.PubSubDemo.model.Payload;
import com.spring.messaging.PubSubDemo.service.NoteService;
import com.spring.messaging.PubSubDemo.service.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/notes")
public class MessageController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private Publisher publisher;

    @PostMapping
    public ResponseEntity<Void> publish(@Valid @RequestBody Payload message) {
        log.info("Received a message with content[{}]", message.getContent());
        publisher.publish(message);

        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        log.info("Retrieving all stored messages");
        final List<Note> result = noteService.getAll();
        return ResponseEntity.ok(result);
    }
}

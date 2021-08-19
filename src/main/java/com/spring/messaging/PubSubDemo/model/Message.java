package com.spring.messaging.PubSubDemo.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.time.Instant;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private Instant timestamp;
}

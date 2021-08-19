package com.spring.messaging.PubSubDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.sql.Timestamp;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private Timestamp timestamp;
}

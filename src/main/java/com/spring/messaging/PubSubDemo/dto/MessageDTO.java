package com.spring.messaging.PubSubDemo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDTO {
   private String content;

   private LocalDateTime timeStamp;
}

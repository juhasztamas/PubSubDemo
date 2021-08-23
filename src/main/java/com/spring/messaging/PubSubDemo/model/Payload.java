package com.spring.messaging.PubSubDemo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class Payload {
    @NotBlank(message = "content is mandatory")
    private String content;

    @NotBlank(message = "timestamp is mandatory")
    private String timestamp;
}

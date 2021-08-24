package com.spring.messaging.PubSubDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    @NotBlank(message = "content is mandatory")
    private String content;

    @NotBlank(message = "timestamp is mandatory")
    private String timestamp;
}

package com.spring.messaging.PubSubDemo.exception;

public class PublisherException extends RuntimeException {
    public PublisherException(final String message) {
        super(message);
    }
}

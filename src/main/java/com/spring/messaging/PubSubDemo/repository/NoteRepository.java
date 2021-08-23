package com.spring.messaging.PubSubDemo.repository;

import com.spring.messaging.PubSubDemo.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}

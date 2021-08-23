package com.spring.messaging.PubSubDemo.service;

import com.spring.messaging.PubSubDemo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteRepository {

    @Autowired
    private com.spring.messaging.PubSubDemo.repository.NoteRepository noteRepository;

    public void save(final Note note) {
        noteRepository.save(note);
    }

    public List<Note> getAll() {
        List<Note> result = new ArrayList<Note>();
        noteRepository.findAll().forEach(result::add);
        return result;
    }
}

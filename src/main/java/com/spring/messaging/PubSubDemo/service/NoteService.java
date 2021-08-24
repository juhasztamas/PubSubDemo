package com.spring.messaging.PubSubDemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.messaging.PubSubDemo.dto.NoteDTO;
import com.spring.messaging.PubSubDemo.model.Note;
import com.spring.messaging.PubSubDemo.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PalindromeFinder palindromeFinder;

    @Autowired
    private ObjectMapper mapper;

    public void save(final String onMessage) {
        try {
            final var converted = mapper.readValue(onMessage, NoteDTO.class);
            final var palindromSize = calculatePalindrome(converted.getContent());
            final var note = Note.builder()
                    .content(converted.getContent())
                    .timestamp(converted.getTimestamp())
                    .longestPalindromeSize(palindromSize)
                    .build();
            noteRepository.save(note);
        } catch (final Exception e) {
            log.error("Could note save note", e);
        }
    }

    public List<Note> getAll() {
        final List<Note> result = new ArrayList<Note>();
        noteRepository.findAll().forEach(result::add);
        return result;
    }

    private int calculatePalindrome(final String content) {
        return palindromeFinder.getLongestPalindromeSize(content);
    }
}

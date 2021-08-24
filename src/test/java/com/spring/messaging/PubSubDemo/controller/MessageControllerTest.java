package com.spring.messaging.PubSubDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.messaging.PubSubDemo.model.Note;
import com.spring.messaging.PubSubDemo.model.Payload;
import com.spring.messaging.PubSubDemo.service.NoteService;
import com.spring.messaging.PubSubDemo.service.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MessageControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper mapper;
    @Mock
    private NoteService noteService;
    @Mock
    private Publisher publisher;
    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(messageController)
                .build();
    }

    @Test
    public void publish_shouldReturn202() throws Exception {
        final var payload = mapper.writeValueAsString(Payload.builder()
                .content("abrakadabra")
                .timestamp("2018-10-09 00:12:12+0100")
                .build());
        mockMvc.perform(post("/api/notes")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void publish_shouldReturnBadRequest_whenInvalidBodyIsSent() throws Exception {
        final var missingProperty = mapper.writeValueAsString(Payload.builder()
                .content("abrakadabra")
                .build());
        mockMvc.perform(post("/api/notes")
                        .content(missingProperty)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAll_shouldReturnStoredNotes() throws Exception {
        Mockito.when(noteService.getAll()).thenReturn(List.of(note()));
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'content':'abrakadabra', 'timestamp':'2018-10-09 00:12:12+0100'}]"))
                .andReturn();
    }

    @Test
    public void getAll_shouldReturn500_whenNoteServiceThrows() throws Exception {
        Mockito.when(noteService.getAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isInternalServerError());
    }

    private Note note() {
        return Note.builder()
                .content("abrakadabra")
                .timestamp("2018-10-09 00:12:12+0100")
                .longestPalindromeSize(3)
                .build();
    }
}
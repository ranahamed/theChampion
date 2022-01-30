package com.bm.TheChampion.controllers;

import com.bm.TheChampion.models.Participant;
import com.bm.TheChampion.services.ParticipantService;
import com.bm.TheChampion.utils.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ParticipantController.class)
class ParticipantControllerTest {
    private Participant participant;
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    @BeforeEach
    public void setup()
    { participant =new Participant((Integer) 1, "Rana", "Hamed",
                28, "0123456789",null);

    }

    @Test
    @DisplayName("test to get all existing participants")
    public void getAllParticipantsTest() throws Exception {
        List<Participant> participantList = new ArrayList<>();
        participantList.add(participant);

        Mockito.when(participantService.getParticipants()).thenReturn(participantList);
        mockMvc.perform(MockMvcRequestBuilders.get("/league/participants"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].firstName", Matchers.equalTo("Rana")));
    }

    @Test
    @DisplayName("test creation of new participant using post request")
    public void createParticipantTest() throws Exception {
        Mockito.when(participantService.createParticipant(Mockito.any(Participant.class))).thenReturn(Messages.PARTICIPANT_CREATION_MSG);
        String json = mapper.writeValueAsString(participant);
        mockMvc.perform(MockMvcRequestBuilders.post("/league/create-participant")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Messages.PARTICIPANT_CREATION_MSG));

    }




}
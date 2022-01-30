package com.bm.TheChampion.controllers;


import com.bm.TheChampion.models.Round;
import com.bm.TheChampion.services.RoundService;
import com.bm.TheChampion.utils.Constants;
import com.bm.TheChampion.utils.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = RoundController.class)
class RoundControllerTest {
    private static ObjectMapper mapper = new ObjectMapper();
    private Round round;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoundService roundService;

    @BeforeEach
    public void setup()
    {
       round = new Round(1, Constants.FIRST_ROUND, true, null);
    }
    @Test
    @DisplayName("test to close round using round id")
    public void closeRoundTest() throws Exception{
        Mockito.when(roundService.closeRound(round.getId())).thenReturn(Messages.ROUND_CLOSED);
        String json = mapper.writeValueAsString(round);
        mockMvc.perform(MockMvcRequestBuilders.get("/rounds/close-round/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Messages.ROUND_CLOSED));
    }
}
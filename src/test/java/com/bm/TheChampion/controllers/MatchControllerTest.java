package com.bm.TheChampion.controllers;

import com.bm.TheChampion.dto.MatchDTO;
import com.bm.TheChampion.models.Match;
import com.bm.TheChampion.services.MatchService;
import com.bm.TheChampion.utils.Messages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = MatchController.class)
class MatchControllerTest {
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Test
    public void getfirstRoundMatchesTest()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/match/first-round")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMatchResultTest() throws Exception {
        MatchDTO matchDTO = new MatchDTO(1, 10, 20);
        Match match = new Match();
        match.setId(1);
        match.setFirstGroupScore(10);
        match.setSecondGroupScore(20);
        Mockito.when(matchService.updateMatchResult(Mockito.any(MatchDTO.class))).thenReturn(match);
        String json = mapper.writeValueAsString(matchDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/match/update-result")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.firstGroupScore", Matchers.equalTo(10)))
                .andExpect(jsonPath("$.secondGroupScore", Matchers.equalTo(20)));


    }
}
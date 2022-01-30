package com.bm.TheChampion.controllers;

import com.bm.TheChampion.dto.MatchDTO;
import com.bm.TheChampion.exceptions.RecordNotFoundException;
import com.bm.TheChampion.models.Match;
import com.bm.TheChampion.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @GetMapping("/first-round")
    public ResponseEntity<List<Match>> getfirstRoundMatches(){
        return new ResponseEntity<>(matchService.getfirstRoundMatches(), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/update-result")
    public ResponseEntity<Match> updateMatchResult(@Valid @RequestBody MatchDTO matchDTO)throws RecordNotFoundException {
        return new ResponseEntity<>(matchService.updateMatchResult(matchDTO), new HttpHeaders(), HttpStatus.OK);
    }

}

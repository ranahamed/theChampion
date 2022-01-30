package com.bm.TheChampion.controllers;

import com.bm.TheChampion.exceptions.RecordNotFoundException;
import com.bm.TheChampion.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rounds")
public class RoundController {
    @Autowired
    RoundService roundService ;

    @GetMapping("/close-round/{id}")
    public ResponseEntity<String > closeRound(@PathVariable("id") Integer id) throws RecordNotFoundException {
        return new ResponseEntity<>(roundService.closeRound(id), new HttpHeaders(), HttpStatus.OK);
    }
}

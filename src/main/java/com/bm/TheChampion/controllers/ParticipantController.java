package com.bm.TheChampion.controllers;

import com.bm.TheChampion.models.Participant;
import com.bm.TheChampion.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/league")
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @GetMapping("/participants")
    public ResponseEntity<List<Participant>> getParticipants() {
        return new ResponseEntity<>(participantService.getParticipants(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/create-participant")
    public ResponseEntity<String> createParticipant(@Valid @RequestBody Participant participant) {
        return new ResponseEntity<>(participantService.createParticipant(participant), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/group-participants/{n}")
    public ResponseEntity<String > groupParticipants(@PathVariable("n") Integer n){
        return new ResponseEntity<>(participantService.groupParticipants(n), new HttpHeaders(), HttpStatus.OK);
    }

}

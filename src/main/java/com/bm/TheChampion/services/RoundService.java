package com.bm.TheChampion.services;

import com.bm.TheChampion.exceptions.RecordNotFoundException;
import com.bm.TheChampion.models.Round;
import com.bm.TheChampion.repositories.RoundRepository;
import com.bm.TheChampion.utils.Constants;
import com.bm.TheChampion.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundService {

    @Autowired
    RoundRepository roundRepository;

    public String closeRound(Integer id)throws RecordNotFoundException {
        Optional<Round> round = roundRepository.findById(id);
        if(round.isPresent()) {
            round.get().setOpen(false);
            roundRepository.save(round.get());
            return Messages.ROUND_CLOSED;
        } else {
            throw new RecordNotFoundException(Constants.ROUND, Constants.ID, id);
        }

    }
}

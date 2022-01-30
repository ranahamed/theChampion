package com.bm.TheChampion.services;

import com.bm.TheChampion.dto.MatchDTO;
import com.bm.TheChampion.exceptions.RecordNotFoundException;
import com.bm.TheChampion.models.Group;
import com.bm.TheChampion.models.Match;
import com.bm.TheChampion.models.Round;
import com.bm.TheChampion.repositories.GroupRepository;
import com.bm.TheChampion.repositories.MatchRepository;
import com.bm.TheChampion.repositories.RoundRepository;
import com.bm.TheChampion.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private  Round firstRound ;
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    RoundRepository roundRepository;

    @Autowired
    GroupRepository groupRepository;

    public List<Match> getfirstRoundMatches(){

        if(roundRepository.findByName(Constants.FIRST_ROUND).size() > 0){
            firstRound = roundRepository.findByName(Constants.FIRST_ROUND).get(0);
            return firstRound.getMatchList();
        }

        List<Match> matchList =  new ArrayList<>();
        firstRound =  new Round();
        firstRound.setRoundName(Constants.FIRST_ROUND);
        firstRound.setOpen(true);
        firstRound = roundRepository.save(firstRound);
        List<Group> groupList = groupRepository.findAll();
//        Collections.sort(groupList, Comparator.comparing(s -> s.getParticipantList().size()));

        int i = 0;
        while(i < groupList.size() - 1 ){
            //checking the opponents are same size
            if(groupList.get(i).getParticipantList().size() == groupList.get(i+1).getParticipantList().size()){

                Match match = create_match(groupList.get(i), groupList.get(i+1));
                matchList.add(match);
                i = i + 2;
            }
            else{
                i++;
            }
        }
        firstRound.setMatchList(matchList);
        return matchList;

    }

    public Match create_match(Group firstGroup,Group secondGroup){
        Match match = new Match();
        match.setRound(firstRound);
        match.setFirstGroup(firstGroup);
        match.setSecondGroup(secondGroup);
        match = matchRepository.save(match);
        return match;
    }

    public Match updateMatchResult(MatchDTO matchDTO)throws RecordNotFoundException{
       Optional<Match> matchOp = matchRepository.findById(matchDTO.getId());
        if(matchOp.isPresent()) {
            Match match = matchOp.get();
            match.setFirstGroupScore(matchDTO.getFirstGroupScore());
            match.setSecondGroupScore(matchDTO.getSecondGroupScore());
            if(matchDTO.getFirstGroupScore() > matchDTO.getSecondGroupScore()){
                match.setWinner(match.getFirstGroup());
            }
            else{
                match.setWinner(match.getSecondGroup());
            }
           match = matchRepository.save(match);
            return match;
        } else {
            throw new RecordNotFoundException(Constants.MATCH, Constants.ID, matchDTO.getId());
        }

    }

}

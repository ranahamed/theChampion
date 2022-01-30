package com.bm.TheChampion.services;

import com.bm.TheChampion.models.Group;
import com.bm.TheChampion.models.Participant;
import com.bm.TheChampion.repositories.GroupRepository;
import com.bm.TheChampion.repositories.ParticipantRepository;
import com.bm.TheChampion.utils.Constants;
import com.bm.TheChampion.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    GroupRepository groupRepository;

    public List<Participant> getParticipants(){
        List<Participant> participantList = participantRepository.findAll();
        if (participantList.size() > 0){
            return participantList;
        }
        else {
            return new ArrayList<Participant>();
        }
    }

    public String createParticipant(Participant participant){
        if( participantRepository.findAll().size() >= 12){
            return Messages.MAX_PARTICIPANT_REACHED;
        }
        else{
            participantRepository.save(participant);
            return Messages.PARTICIPANT_CREATION_MSG;
        }
    }

    public String groupParticipants(Integer n){
        List<Participant> participantList = participantRepository.findAll();
        int participantsSize = participantList.size();
        if(participantsSize < 1){
            return Messages.NO_PARTICIPANTS_EXISTS;
        }
        if(n < 1){
            return Messages.WRONG_GROUP_NUMBER;
        }
        String output="";
        if(participantsSize % n != 0 && participantsSize > n){
            output = output + Messages.PARTICIPANT_DIVIDED  + n + Messages.NOT_EQUALLY;
        }
        else if(participantsSize < n)
        {
            output = Messages.GROUP_NO_GREATER_THAN_PARTICIPANT_NO + participantsSize + Messages.AND;
        }

        Collections.shuffle(participantList);
        creatingGroups(participantList, n);
        output = output + Messages.DONE;
        return output;

    }

    public void creatingGroups(List<Participant> participantList, int n){
        List<Group> groupList = new ArrayList<>();
        int groupCounter = 0;
// flag to reset groupCounter
        boolean flag = false;
        for(int i = 0 ; i < participantList.size() ; i++){
            Group savedGroup = new Group();
            if (!flag) {
                Group group = new Group();
                savedGroup = groupRepository.save(group);
                groupList.add(savedGroup);
            }
            else{
                savedGroup = groupList.get(groupCounter);
            }
            groupCounter ++;
            if(groupCounter == n){
                flag = true;
                groupCounter = 0;
            }
            if(savedGroup.getParticipantList() == null){
                List<Participant> list = new ArrayList<>();
                list.add(participantList.get(i));
                savedGroup.setParticipantList(list);
            }
            else {
                savedGroup.getParticipantList().add(participantList.get(i));
            }

            savedGroup = groupRepository.save(savedGroup);
            participantList.get(i).setGroup(savedGroup);
            participantRepository.save(participantList.get(i));
        }
    }

}

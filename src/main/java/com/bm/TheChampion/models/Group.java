package com.bm.TheChampion.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "participants_group")
@Data
public class Group implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 32)
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Participant> participantList ;


}

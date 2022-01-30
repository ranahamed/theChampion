package com.bm.TheChampion.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "first_group_id")
    private Group firstGroup;

    @OneToOne
    @JoinColumn(name = "second_group_id")
    private Group secondGroup;

    @OneToOne
    @JoinColumn(name = "winner_group_id")
    private Group winner;

    private Integer firstGroupScore;
    private Integer secondGroupScore;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "round_id")
    @JsonIgnore
    private Round round;


}

package com.bm.TheChampion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rounds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 32)
    private String roundName;

    @Column(columnDefinition = "boolean default false")
    private boolean isOpen ;

    @OneToMany(mappedBy = "round")
    private List<Match> matchList ;
}

package com.bm.TheChampion.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "participants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer age;

    private String phoneNo;

    //    unidirectional mapping because we need to retrieve data from child only (participant)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;


}

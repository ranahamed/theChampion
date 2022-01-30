package com.bm.TheChampion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private Integer id;
    private Integer firstGroupScore;
    private Integer secondGroupScore;

}


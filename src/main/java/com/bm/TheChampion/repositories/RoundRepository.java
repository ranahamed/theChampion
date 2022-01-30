package com.bm.TheChampion.repositories;

import com.bm.TheChampion.models.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {
    @Query(value="select * from rounds where round_name = :keyword", nativeQuery = true )
    List<Round> findByName(@Param("keyword") String keyword);
}


package com.bm.TheChampion.repositories;

import com.bm.TheChampion.models.Group;
import com.bm.TheChampion.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}

package com.kuebiko.amazonemployee.dao_repository;


import com.kuebiko.amazonemployee.dto_entity.Punch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunchRepository extends JpaRepository<Punch, Long> {
}

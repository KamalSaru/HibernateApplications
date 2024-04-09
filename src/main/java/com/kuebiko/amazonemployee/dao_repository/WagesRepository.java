package com.kuebiko.amazonemployee.dao_repository;

import com.kuebiko.amazonemployee.dto_entity.WagesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagesRepository extends JpaRepository<WagesDTO, Long> {
}

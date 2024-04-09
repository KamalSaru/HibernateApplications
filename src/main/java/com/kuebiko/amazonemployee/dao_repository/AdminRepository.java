package com.kuebiko.amazonemployee.dao_repository;

import com.kuebiko.amazonemployee.dto_entity.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminLogin, Long> {

    //@PostMapping----Admin login authentication verify--------------
    Optional<AdminLogin> findByEmailAndPassword(String email, String password);

}
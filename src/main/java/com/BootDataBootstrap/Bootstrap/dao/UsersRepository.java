package com.BootDataBootstrap.Bootstrap.dao;

import com.BootDataBootstrap.Bootstrap.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByConfirmationToken(String confirmationToken);
}

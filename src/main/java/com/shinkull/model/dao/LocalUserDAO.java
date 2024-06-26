package com.shinkull.model.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.shinkull.model.LocalUser;

public interface LocalUserDAO extends CrudRepository<LocalUser, Long> {
    
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String email);

}

package com.crud.Learn.repository;

import com.crud.Learn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {  //<modelName, ID_type>

    // Custom query method to find a user by email
    Optional<User> findByEmail(String email);
}

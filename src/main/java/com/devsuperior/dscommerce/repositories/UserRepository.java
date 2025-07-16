package com.devsuperior.dscommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscommerce.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        FROM User u
        LEFT JOIN FETCH u.authorities
        WHERE u.email = :email      
    """)
    Optional<User> searchByEmail(String email);

}

package com.CSC340.MinervasList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSC340.MinervasList.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(@Param("email") String email);

    @Query(value = "SELECT user_type FROM users WHERE LOWER(email) = LOWER(:email)", nativeQuery = true)
    Optional<String> findUserTypeByEmailIgnoreCase(@Param("email") String email);

    boolean existsByEmail(String email);
}

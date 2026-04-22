package main.java.com.CSC340.MinervasList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CSC340.MinervasList.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

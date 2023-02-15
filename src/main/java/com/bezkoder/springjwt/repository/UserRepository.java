package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "SELECT * FROM users WHERE username = ?1 AND enabled = 1", nativeQuery = true)
  Integer findUserByEnabled(String username);

  //update password by email
  @Query(value = "UPDATE users SET password = ?1 WHERE email = ?2", nativeQuery = true)
  Integer updatePasswordByEmail(String password, String email);

  User findByEmail(String email);
}

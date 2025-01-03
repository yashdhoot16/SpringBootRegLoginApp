package com.springboot.main.repositories;

import com.springboot.main.entities.UserCredential;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
	Optional<UserCredential> findByEmail(String email);
}

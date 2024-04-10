package com.rafaelpavan.repositories.user;

import com.rafaelpavan.models.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserById(UUID id);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

}

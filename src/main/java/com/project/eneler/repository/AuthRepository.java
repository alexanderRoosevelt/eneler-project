package com.project.eneler.repository;

import com.project.eneler.model.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

}

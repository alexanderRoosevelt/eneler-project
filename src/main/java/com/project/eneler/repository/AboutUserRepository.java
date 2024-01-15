package com.project.eneler.repository;

import com.project.eneler.model.entity.AboutUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUserRepository extends JpaRepository<AboutUserEntity, Long> {



}

package com.project.eneler.repository;


import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.entity.UserVideoViewEntity;
import com.project.eneler.model.entity.UserVideoViewId;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVideoViewRepository extends
    JpaRepository<UserVideoViewEntity, UserVideoViewId> {

  List<UserVideoViewEntity> findAllByUser(UserEntity user);



}
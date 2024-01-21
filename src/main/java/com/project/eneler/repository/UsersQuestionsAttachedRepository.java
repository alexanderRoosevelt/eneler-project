package com.project.eneler.repository;

import com.project.eneler.model.entity.UsersQuestionsAttached;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersQuestionsAttachedRepository extends JpaRepository<UsersQuestionsAttached, Long> {
  List<UsersQuestionsAttached> findAllByUserId(Long userId);
}

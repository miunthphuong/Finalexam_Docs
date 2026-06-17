package edu.miu.cs.docs.repository;

import edu.miu.cs.docs.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
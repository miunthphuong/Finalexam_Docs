package edu.miu.cs.docs.repository;

import edu.miu.cs.docs.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
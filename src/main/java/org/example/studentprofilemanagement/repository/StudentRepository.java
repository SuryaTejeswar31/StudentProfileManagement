package org.example.studentprofilemanagement.repository;

import org.example.studentprofilemanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}

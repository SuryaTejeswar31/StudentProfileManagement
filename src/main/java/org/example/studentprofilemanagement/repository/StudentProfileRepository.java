package org.example.studentprofilemanagement.repository;

import org.example.studentprofilemanagement.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {
}

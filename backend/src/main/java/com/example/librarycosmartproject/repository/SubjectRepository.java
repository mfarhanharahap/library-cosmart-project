package com.example.librarycosmartproject.repository;

import com.example.librarycosmartproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(value = "select s.* from subject s where s.name ilike :subjectName",
            nativeQuery = true)
    Optional<Subject> findSubjectByName(@Param("subjectName") String subjectName);
}

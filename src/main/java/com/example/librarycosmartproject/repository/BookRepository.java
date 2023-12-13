package com.example.librarycosmartproject.repository;


import com.example.librarycosmartproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select b.* from book b join subject s on b.subject_id = s.id " +
            "where s.name = :subjectName", nativeQuery = true)
    Optional<List<Book>> findAllBooksBySubjects(@Param("subjectName") String subjectName);
}

package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Subject;
import com.example.librarycosmartproject.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Subject Service Unit test")
class SubjectServiceTest {
    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    private Subject subject;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setId(1);
        subject.setName("love");
    }

    @Test
    void getSubjectByName() {
        Mockito.when(subjectRepository.findSubjectByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(subject);
        Subject newSubject = subjectService.getSubjectByName("love");
        Assertions.assertEquals(newSubject.getName(), subject.getName());
    }
}
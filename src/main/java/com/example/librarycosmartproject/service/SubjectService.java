package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Subject;
import com.example.librarycosmartproject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject getSubjectByName(String subjectName) {
        return subjectRepository.findSubjectByName(subjectName)
                .orElseGet(() -> addSubject(subjectName));
    }
    private Subject addSubject(String subjectName) {
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setCreateDate(new Date());
        subject.setUpdateDate(new Date());
        return subjectRepository.save(subject);
    }
}

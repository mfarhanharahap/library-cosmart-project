package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getAuthorByName(String authorName) {
        return authorRepository.findAuthorByName(authorName)
                .orElseGet(() -> addAuthor(authorName));
    }

    public List<String> getListAuthorByBookTitle(String title) {
        return authorRepository.findAuthorsNameByBookTitle(title).orElse(new ArrayList<>());
    }
    private Author addAuthor(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        author.setCreateDate(new Date());
        author.setUpdateDate(new Date());
        return authorRepository.save(author);
    }
}

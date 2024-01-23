package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByAuthorAndTitle(String author, String title);
}

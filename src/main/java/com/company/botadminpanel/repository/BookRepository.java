package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByTitle(String title);

    @Query(value = "from Book order by id")
    List<Book> findAllOrderById();
}

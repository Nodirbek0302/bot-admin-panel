package com.company.botadminpanel.repository;

import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {

    Optional<Section> findByNameAndBook_Id(String name, Integer book_id);

    List<Section> findByBook_Id(Integer book_id);
}

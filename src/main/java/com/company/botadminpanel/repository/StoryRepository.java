package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.Story;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    @Query(value = """
            select s from Story s join Section v on s.section.id = v.id 
            where s.score is not null order by 
            case when :sort = 'score' and :s = 'ASC'  then s.score end asc,
            case when :sort = 'score' and :s = 'DESC' then s.score end desc,
            case when :sort = 'name' and :s = 'ASC'  then v.name end asc,
            case when :sort = 'name' and :s = 'DESC' then v.name end desc
            """)
    List<Story> findAll(String sort, String s);

    @Query(value = """
            select s from Story s join Section v on s.section.id = v.id 
            where s.score is null order by 
            case when :sort = 'score' and :s = 'ASC'  then s.score end asc,
            case when :sort = 'score' and :s = 'DESC' then s.score end desc,
            case when :sort = 'name' and :s = 'ASC'  then v.name end asc,
            case when :sort = 'name' and :s = 'DESC' then v.name end desc
            """)
    List<Story> findAllIsNull(String sort, String s);
}

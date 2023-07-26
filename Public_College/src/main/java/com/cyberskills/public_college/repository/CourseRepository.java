package com.cyberskills.public_college.repository;

import com.cyberskills.public_college.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTitleContainingIgnoreCase(String keyword);

    @Query("UPDATE Course t SET t.published = :published WHERE t.id = :id")
    @Modifying
    public void updatePublishedStatus(Integer id, boolean published);
}

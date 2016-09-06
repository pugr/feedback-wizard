package com.monster.mgs.test.repository;

import com.monster.mgs.test.model.TrainingCourseSection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingCourseSectionRepository extends CrudRepository<TrainingCourseSection, Long> {

    @Query("select t from trainingCourseSection t where t.trainingCourse.id = :id")
    Set<TrainingCourseSection> findAllForCourse(@Param("id") Long courseId);

}

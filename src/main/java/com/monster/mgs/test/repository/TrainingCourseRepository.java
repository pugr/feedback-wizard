package com.monster.mgs.test.repository;

import com.monster.mgs.test.model.TrainingCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingCourseRepository extends CrudRepository<TrainingCourse, Long> {

    @Query(value = "select c from TRAINING_COURSE as c left join fetch c.trainingCourseSections where c.id = :id")
    TrainingCourse findById(@Param("id") Long id);

}

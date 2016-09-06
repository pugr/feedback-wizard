package com.monster.mgs.test.repository;

import com.monster.mgs.test.model.TrainingCourseFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<TrainingCourseFeedback, Long> {

}

package com.monster.mgs.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monster.mgs.test.model.Visitor;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long> {

	Visitor findByEmailAddress(String emailAddress);
}

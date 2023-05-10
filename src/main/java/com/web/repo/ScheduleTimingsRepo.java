package com.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.model.ScheduleTimings;

@Repository
public interface ScheduleTimingsRepo extends JpaRepository<ScheduleTimings, Integer> {

	@Query("SELECT cronExpression FROM ScheduleTimings WHERE id= :id")
	public String findCronExpressionById(@PathVariable int id);
}

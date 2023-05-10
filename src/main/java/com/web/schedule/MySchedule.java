package com.web.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.web.repo.ScheduleTimingsRepo;
import com.web.service.EmployeeService;

@Component
public class Myschedule {
	
	@SuppressWarnings("unused")
	@Autowired
	private ScheduleTimingsRepo scheduleTimingsRepo;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Scheduled(cron="#{ @scheduleTimingsRepo.findCronExpressionById(1) }")
	public void task1() {
	    LocalDateTime dt = LocalDateTime.now();
	    System.out.println("Cron EXpression1 "+dt);
	}
	
	
	@Scheduled(cron="#{ @scheduleTimingsRepo.findCronExpressionById(3) }")
	public void task2() {
	    LocalDateTime dt = LocalDateTime.now();
	    var employees=employeeService.findAll();
	    System.out.println(employees+" "+dt);
	}

}

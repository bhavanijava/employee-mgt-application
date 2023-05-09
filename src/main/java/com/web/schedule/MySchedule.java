package com.web.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.web.model.Employee;
import com.web.service.EmployeeService;

@Component
public class MySchedule {
	
	@Autowired
	private EmployeeService service;
	
	@Scheduled(cron = "${cron.cronExpression1}")
	public void task1()
	{
		LocalDateTime dt=LocalDateTime.now();
		System.out.println("Hello "+dt);
	}
	
	@Scheduled(cron = "${cron.cronExpression2}")
	public void task2()
	{
		LocalDateTime dt=LocalDateTime.now();
		List<Employee> emps=service.findAll();
		System.out.println(emps+" "+dt);
	}
}

package com.web.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.web.repo.ScheduleTimingsRepo;
import com.web.service.EmployeeService;


@Component
public class MySchedule {

    private final ScheduleTimingsRepo scheduleTimingsRepo;
    private final EmployeeService employeeService;

    @Autowired
    public MySchedule(ScheduleTimingsRepo scheduleTimingsRepo, EmployeeService employeeService) {
        this.scheduleTimingsRepo = scheduleTimingsRepo;
        this.employeeService = employeeService;
    }

    @Scheduled(cron="#{ @scheduleTimingsRepo.findCronExpressionById(1) }")
    public void task1() {
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Cron Expression 1: " + dt);
    }

    @Scheduled(cron="#{ @scheduleTimingsRepo.findCronExpressionById(3) }")
    public void task2() {
        LocalDateTime dt = LocalDateTime.now();
        var employees=employeeService.findAll();
        System.out.println("Employees: " + employees + " " + dt);
    }

	
	public ScheduleTimingsRepo getScheduleTimingsRepo() {
		return scheduleTimingsRepo;
	}

}

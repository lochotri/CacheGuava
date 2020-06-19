package com.sample.app.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
@CacheConfig(cacheNames = "myEmployeeCache")
public class EmployeeService {

	public static List<Employee> emps = new ArrayList<>();

	static {
		emps.add(new Employee(1, "Ram", "Kota"));
		emps.add(new Employee(2, "Raj", "Majety"));
		emps.add(new Employee(3, "PTR", "Navakotla"));
		emps.add(new Employee(4, "Krishna", "Boppana"));
	}

	public void test() throws IOException {
		kk();
		for(int king = 0; king < 1000000;king++){
			System.out.println("AAAvvv");

		}


	}
	@Cacheable
	public Employee getEmployeeById(int id){
		System.out.println("getEmployeeById() is called");

		for (Employee emp : emps) {
			if (id == emp.getId()) {
				return emp;
			}
		}
		return null;
	}

	@Cacheable
	public Employee getEmployeeByFirstName(String name) {
		System.out.println("getEmployeeByFirstName() invoked");

		for (Employee emp : emps) {
			if (name.equals(emp.getFirstName())) {
				return emp;
			}
		}
		return null;
	}

	@Async
	public void kk() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		BufferedWriter writer = new BufferedWriter(new FileWriter("E:/hat.txt"));
		writer.write(fileContent);
		writer.close();

	}


}
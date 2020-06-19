package com.sample.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.model.Organization;
import com.sample.app.service.EmployeeService;
import com.sample.app.service.OrganizationService;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private OrganizationService orgService;

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void getEmployeeAndOrgDetails() throws IOException {

		Employee emp = empService.getEmployeeById(1);
		System.out.println(emp);
		emp = empService.getEmployeeByFirstName("Krishna");
		System.out.println(emp);

		Organization org = orgService.getById(3);
		System.out.println(org);
		org = orgService.getByName("GHI Corp");
		System.out.println(org);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			for (Employee employee : EmployeeService.emps) {
				cacheManager.getCache("myEmployeeCache").put(Integer.toString(employee.getId()), employee);
			}
			printNativeCache();
			empService.getEmployeeById(1);
			EmployeeService.emps.add(new Employee(7,"AAA","BBBb"));
			empService.getEmployeeById(7);
//			System.out.println(test());
//
//			printNativeCache();
//			getEmployeeAndOrgDetails();
//
//			printNativeCache();
//			//getEmployeeAndOrgDetails();
//			Employee emp = empService.getEmployeeById(4);
//			System.out.println("\nSleeping for 5 seconds");
//			TimeUnit.SECONDS.sleep(5);
//			printNativeCache();
//
//			System.out.println("\nSleeping for 5 seconds");
//			TimeUnit.SECONDS.sleep(5);
//			printNativeCache();

		};
	}

	public void printNativeCache() {
		System.out.println("\n**************************************");
		System.out.println("-- native cache --");
		Collection<String> cacheNames = cacheManager.getCacheNames();

		for (String cacheName : cacheNames) {
			System.out.println("\nFor the cache : " + cacheName);
			Cache cache = cacheManager.getCache(cacheName);
			Map<String, Object> map = (Map<String, Object>) cache.getNativeCache();
			map.forEach((key, value) -> {
				System.out.println(key + " = " + value);

			});


		}

		System.out.println("**************************************\n");
	}

	public void kk() throws InterruptedException {

		TimeUnit.SECONDS.sleep(8);
		System.out.println("testing");

	}

	public String test() throws IOException, InterruptedException {
		new Thread(new Runnable() {
			public void run()
			{

				// perform any operation
				try {
					kk();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		return "DOne";

	}
}

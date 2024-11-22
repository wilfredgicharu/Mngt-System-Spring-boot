package com.fred.schoolmanagement;

import org.springframework.boot.SpringApplication;

public class TestSchoolmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(SchoolmanagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

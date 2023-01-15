package com.smruti.securitydemo;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smruti.securitydemo.model.Student;
import com.smruti.securitydemo.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SecuritydemoApplication implements CommandLineRunner {

	private final StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(SecuritydemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("inside runner");

		studentService.getStudents().addAll(IntStream
				.range(1, 6)
				.mapToObj(val -> new Student("Student - " + val, val + 100))
				.toList());
	}

}

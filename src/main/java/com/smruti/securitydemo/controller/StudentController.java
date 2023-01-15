package com.smruti.securitydemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smruti.securitydemo.model.Student;
import com.smruti.securitydemo.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * StudentController
 */

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Student> allStudent() {
        return studentService.getStudents();
    }

    @GetMapping("/{roll}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable int roll) {
        return studentService.getStudent(roll);
    }

    @ExceptionHandler(RuntimeException.class)
    private ProblemDetail problemDetail(RuntimeException r) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, r.getMessage());
    }
}
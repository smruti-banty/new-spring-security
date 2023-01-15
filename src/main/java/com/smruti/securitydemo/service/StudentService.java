package com.smruti.securitydemo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.smruti.securitydemo.model.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getStudent(int roll) {

        return students.stream().filter(student -> student.getRoll() == roll).findFirst()
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
    }

}

package com.smruti.securitydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Model
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
public class Student {
    private String name;
    private int roll;
    
}
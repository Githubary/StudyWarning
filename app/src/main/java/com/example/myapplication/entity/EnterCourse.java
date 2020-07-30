package com.example.myapplication.entity;

import lombok.Data;

@Data
public class EnterCourse {
    private Integer id;
    private String courseName;
    private String courseNumber;
    private String courseTeacher;
    private String courseCover;
    private Integer studentCount;
    private String courseSynopsis;
}

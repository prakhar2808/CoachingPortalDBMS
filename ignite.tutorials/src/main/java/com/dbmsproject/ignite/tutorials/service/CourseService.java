package com.dbmsproject.ignite.tutorials.service;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Course;

@Component
public interface CourseService {
		   
    public void addCourse(Course course);
    
    public List<Course> getAllCourses();
	
	public Course getCourseByCourseId(String CourseId);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(String CourseId);
	 
}


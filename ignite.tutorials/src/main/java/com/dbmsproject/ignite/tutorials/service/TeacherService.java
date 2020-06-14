package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dbmsproject.ignite.tutorials.model.Teacher;

@Component
public interface TeacherService {
		   
    public void addTeacher(Teacher teacher);
 
    public List<Teacher> getAllTeachers();
 
    public Teacher getTeacherByTeacherId(int TeacherId);
 
    public void updateTeacher(Teacher teacher);
 
    public void deleteTeacher(int TeacherId);
	 
}


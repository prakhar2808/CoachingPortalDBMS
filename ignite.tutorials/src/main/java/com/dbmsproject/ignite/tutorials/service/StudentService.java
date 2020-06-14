package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dbmsproject.ignite.tutorials.model.Student;

@Component
public interface StudentService {
		   
    public void addStudent(Student student);
 
    public List<Student> getAllStudents();
 
    public Student getStudentByRollNo(int RollNo);
 
    public void updateStudent(Student student);
 
    public void deleteStudent(int RollNo);

	public List<Student> getStudentByGuardianId(int guardianId);
	 
}


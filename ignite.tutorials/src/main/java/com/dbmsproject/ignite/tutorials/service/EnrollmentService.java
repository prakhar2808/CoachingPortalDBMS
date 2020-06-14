package com.dbmsproject.ignite.tutorials.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Enrollment;
import com.dbmsproject.ignite.tutorials.model.Student;

@Component
public interface EnrollmentService {
	public void addEnrollment(Enrollment enrollment);
	
	 public List<Student> getAllStudentsByCourseId(String courseId);
	 
	 public List<Student> getAllStudentsByCourseIdAndBatchId(String courseId, int batchId);
	 
	 public List<Student >getAllStudentsByCourseIdAndBatchIdAndDate(String courseId, int batchId, java.sql.Date date);

	public List<Enrollment> getAllEnrollments();

	public Enrollment getEnrollmentsByRollCourseBatch(int rollNo, String courseId, int batchId);

	public void updateEnrollment(Enrollment enrollment);

	public void deleteEnrollment(int rollNo, String courseId, int batchId);

	public List<Student> getAllStudentsByCourseIdAndDate(String courseId, Date date);

	public List<Enrollment> getCoursesByRoll(int rollNo);
}

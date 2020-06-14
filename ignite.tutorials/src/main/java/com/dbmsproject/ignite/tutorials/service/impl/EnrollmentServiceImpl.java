package com.dbmsproject.ignite.tutorials.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.EnrollmentDao;
import com.dbmsproject.ignite.tutorials.model.Enrollment;
import com.dbmsproject.ignite.tutorials.model.Student;
import com.dbmsproject.ignite.tutorials.service.EnrollmentService;

@Component
public class EnrollmentServiceImpl implements EnrollmentService{

	@Autowired
	private EnrollmentDao enrollmentDao;
	
	@Transactional
	public void addEnrollment(Enrollment enrollment) {
		enrollmentDao.addEnrollment(enrollment);
	}
	
	@Transactional
	 public List<Student> getAllStudentsByCourseId(String courseId){
		return enrollmentDao.getAllStudentsByCourseId(courseId);
	}
	
	@Transactional
	public List<Student >getAllStudentsByCourseIdAndBatchIdAndDate(String courseId, int batchId, java.sql.Date date){
		return enrollmentDao.getAllStudentsByCourseIdAndBatchIdAndDate(courseId, batchId, date);
	}
	
	@Transactional
	public List<Student> getAllStudentsByCourseIdAndBatchId(String courseId, int batchId){
		return enrollmentDao.getAllStudentsByCourseIdAndBatchId(courseId, batchId);
	}

	@Transactional
	public List<Enrollment> getAllEnrollments() {
		return enrollmentDao.getAllEnrollments();
	}

	@Transactional
	public Enrollment getEnrollmentsByRollCourseBatch(int rollNo, String courseId, int batchId) {
		return enrollmentDao.getEnrollmentsByRollCourseBatch(rollNo,courseId,batchId);
	}

	@Transactional
	public void updateEnrollment(Enrollment enrollment) {
		enrollmentDao.updateEnrollment(enrollment);
	}

	@Transactional
	public void deleteEnrollment(int rollNo, String courseId, int batchId) {
		enrollmentDao.deleteEnrollment(rollNo,courseId,batchId);
	}

	@Transactional
	public List<Student> getAllStudentsByCourseIdAndDate(String courseId, Date date) {
		return enrollmentDao.getAllStudentsByCourseIdAndDate(courseId, date);
	}

	@Transactional
	public List<Enrollment> getCoursesByRoll(int rollNo) {
		return enrollmentDao.getCoursesByRoll(rollNo);
	}
}

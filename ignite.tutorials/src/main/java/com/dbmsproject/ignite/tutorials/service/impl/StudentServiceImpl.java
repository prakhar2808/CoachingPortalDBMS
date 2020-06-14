package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.service.StudentService;
import com.dbmsproject.ignite.tutorials.dao.StudentDao;
import com.dbmsproject.ignite.tutorials.model.Student;

@Component
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao StudentDao;
	
	// Create of CRUD
	@Transactional
	public void addStudent(Student student) {
	    StudentDao.addStudent(student);
	}
	
	// Read of CRUD
	@Transactional
	public List<Student> getAllStudents() {
	    return StudentDao.getAllStudents();
	}
	
	// Read of CRUD
	@Transactional
	public Student getStudentByRollNo(int RollNo) {
	    return StudentDao.getStudentByRollNo(RollNo);
	}
	
	// Update of CRUD
	@Transactional
	public void updateStudent(Student student) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(student == null)
			return;
		Student updated_student = StudentDao.getStudentByRollNo(student.getRollNo());
		updated_student.setPassword(encoder.encode(student.getPassword()));
		updated_student.setFirstName(student.getFirstName());
		updated_student.setLastName(student.getLastName());
		updated_student.setGender(student.getGender());
		updated_student.setDob(student.getDob());
		updated_student.setHouseNo(student.getHouseNo());
		updated_student.setStreet(student.getStreet());
		updated_student.setCity(student.getCity());
	    StudentDao.updateStudent(updated_student);
	}
	
	// Delete of CRUD
	@Transactional
	public void deleteStudent(int RollNo) {
	    StudentDao.deleteStudent(RollNo);
	}
	
	public StudentDao getStudentDao() {
	    return StudentDao;
	}

	@Autowired(required=true)
	public void setStudentDao(StudentDao StudentDao) {
	    this.StudentDao = StudentDao;
	}

	@Transactional
	public List<Student> getStudentByGuardianId(int guardianId) {
		return StudentDao.getStudentByGuardianId(guardianId);
	}
}

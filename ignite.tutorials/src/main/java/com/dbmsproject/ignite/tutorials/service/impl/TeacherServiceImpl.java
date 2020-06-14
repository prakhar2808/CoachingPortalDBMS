package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.service.TeacherService;
import com.dbmsproject.ignite.tutorials.dao.TeacherDao;
import com.dbmsproject.ignite.tutorials.model.Teacher;

@Component
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao TeacherDao;
	
	// Create of CRUD
	@Transactional
	public void addTeacher(Teacher teacher) {
	    TeacherDao.addTeacher(teacher);
	}
	
	// Read of CRUD
	@Transactional
	public List<Teacher> getAllTeachers() {
	    return TeacherDao.getAllTeachers();
	}
	
	// Read of CRUD
	@Transactional
	public Teacher getTeacherByTeacherId(int TeacherId) {
	    return TeacherDao.getTeacherByTeacherId(TeacherId);
	}
	
	// Update of CRUD
	@Transactional
	public void updateTeacher(Teacher teacher) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(teacher == null)
			return;
		Teacher updated_teacher = TeacherDao.getTeacherByTeacherId(teacher.getTeacherId());
		updated_teacher.setPassword(encoder.encode(teacher.getPassword()));
		updated_teacher.setFirstName(teacher.getFirstName());
		updated_teacher.setLastName(teacher.getLastName());
		updated_teacher.setGender(teacher.getGender());
		updated_teacher.setDob(teacher.getDob());
		updated_teacher.setHouseNo(teacher.getHouseNo());
		updated_teacher.setStreet(teacher.getStreet());
		updated_teacher.setCity(teacher.getCity());
	    TeacherDao.updateTeacher(updated_teacher);
	}
	
	// Delete of CRUD
	@Transactional
	public void deleteTeacher(int TeacherId) {
	    TeacherDao.deleteTeacher(TeacherId);
	}
	
	public TeacherDao getTeacherDao() {
	    return TeacherDao;
	}
	
	@Autowired(required=true)
	public void setTeacherDao(TeacherDao TeacherDao) {
	    this.TeacherDao = TeacherDao;
	}
}

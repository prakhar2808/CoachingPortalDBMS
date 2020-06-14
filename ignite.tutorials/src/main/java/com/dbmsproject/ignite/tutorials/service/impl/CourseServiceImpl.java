package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.AdminDao;
import com.dbmsproject.ignite.tutorials.dao.CourseDao;
import com.dbmsproject.ignite.tutorials.model.Admin;
import com.dbmsproject.ignite.tutorials.model.Course;
import com.dbmsproject.ignite.tutorials.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;
	
	@Transactional
	public void addCourse(Course course) {
	    courseDao.addCourse(course);
	}

	@Transactional
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}
	

	@Transactional
	public Course getCourseByCourseId(String CourseId) {
		return courseDao.getCourseByCourseId(CourseId);
	}
	
	@Transactional
	public void updateCourse(Course course){
		if(course == null)
			return;
		Course updated_course = courseDao.getCourseByCourseId(course.getCourseId());
		updated_course.setCourseId(course.getCourseId());
		updated_course.setCourseName(course.getCourseName());
		updated_course.setFeesPerMonth(course.getFeesPerMonth());
		updated_course.setRepresentation(course.getRepresentation());
		courseDao.updateCourse(updated_course);
	}
	
	@Transactional
	public void deleteCourse(String CourseId) {
		courseDao.deleteCourse(CourseId);
	}
}

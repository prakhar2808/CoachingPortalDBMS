package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.CourseDao;
import com.dbmsproject.ignite.tutorials.model.Course;
import com.dbmsproject.ignite.tutorials.model.Guardian;

@Component
public class CourseDaoImpl implements CourseDao{
	
	@Autowired
	private SessionFactory sessionFactory;
    
    @Transactional
    public void addCourse(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into course values(:CourseId, :CourseName, :FeesPerMonth)")
        .setParameter("CourseId", course.getCourseId())
        .setParameter("CourseName", course.getCourseName())
        .setParameter("FeesPerMonth", course.getFeesPerMonth())
        .executeUpdate();
    }

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Query<Course> query = session.createNativeQuery("select * from course").addEntity(Course.class);
    	List<Course> result = (List<Course>)(query.getResultList());
    	return result;
	}
	
	@Transactional
	public void deleteCourse(String CourseId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from course where courseId=:CourseId")
		.setParameter("CourseId", CourseId)
		.executeUpdate();
	}
	
	@Transactional
	public Course getCourseByCourseId(String CourseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> query = session.createNativeQuery("select * from course where courseId=:CourseId", Course.class)
				.setParameter("CourseId", CourseId);
		Course result = query.getSingleResult();
		return result;
	}
	
    @Transactional
    public void updateCourse(Course course) {
        sessionFactory.getCurrentSession().update(course);
    }
}

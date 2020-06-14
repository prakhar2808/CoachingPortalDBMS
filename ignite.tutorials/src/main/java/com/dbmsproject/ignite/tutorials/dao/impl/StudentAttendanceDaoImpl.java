package com.dbmsproject.ignite.tutorials.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StudentAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.StudentAttendance;

@Component
public class StudentAttendanceDaoImpl implements StudentAttendanceDao{
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	public void addStudentAttendance(StudentAttendance obj) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("insert into studentAttendance values(:RollNo,:Date,:isPresent,:CourseId,:BatchId)")
		.setParameter("RollNo", obj.getRollNo())
		.setParameter("Date",obj.getDate())
		.setParameter("isPresent", obj.getIsPresent())
		.setParameter("CourseId", obj.getCourseId())
		.setParameter("BatchId",obj.getBatchId())
		.executeUpdate();
	}

	@Transactional
	public ArrayList<StudentAttendance> getAttendance(String courseId, int batchId, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query<StudentAttendance> query = session.createNativeQuery("select * from studentAttendance where courseId=:courseId and batchId=:batchId and date=:date order by(rollNo)",StudentAttendance.class)
				.setParameter("courseId", courseId)
				.setParameter("batchId", batchId)
				.setParameter("date", date);
		ArrayList<StudentAttendance> result = (ArrayList<StudentAttendance>) query.getResultList();
		return result;
	}

	@Transactional
	public List<StudentAttendance> getAttendanceByRollAndCourse(int rollNo, String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<StudentAttendance> query = session.createNativeQuery("select * from studentAttendance where rollNo=:rollNo and courseId=:courseId order by(date) desc",StudentAttendance.class)
				.setParameter("rollNo", rollNo)
				.setParameter("courseId", courseId);
		List<StudentAttendance> result = query.getResultList();
		return result;
	}
}

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

import com.dbmsproject.ignite.tutorials.dao.TeacherAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.TeacherAttendance;

@Component
public class TeacherAttendanceDaoImpl implements TeacherAttendanceDao{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	public void addTeacherAttendance(TeacherAttendance obj) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("insert into teacherAttendance values(:TeacherId,:Date,:isPresent)")
		.setParameter("TeacherId", obj.getTeacherId())
		.setParameter("Date",obj.getDate())
		.setParameter("isPresent", obj.getIsPresent())
		.executeUpdate();
	}

	@Transactional
	public ArrayList<TeacherAttendance> getAttendance(Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query<TeacherAttendance> query =session.createNativeQuery("select * from teacherAttendance where date=:date order by(teacherId)",TeacherAttendance.class)
		.setParameter("date", date);
		ArrayList<TeacherAttendance> result = (ArrayList<TeacherAttendance>) query.getResultList();
		return result;
	}

	@Transactional
	public List<TeacherAttendance> getAllAttendanceByTeacherId(int teacherId) {
		Session session = sessionFactory.getCurrentSession();
		Query<TeacherAttendance> query = session.createNativeQuery("select * from teacherAttendance where teacherId=:teacherId order by(date) desc",TeacherAttendance.class)
				.setParameter("teacherId",teacherId);
		List<TeacherAttendance> result = query.getResultList();
		return result;
	}
}

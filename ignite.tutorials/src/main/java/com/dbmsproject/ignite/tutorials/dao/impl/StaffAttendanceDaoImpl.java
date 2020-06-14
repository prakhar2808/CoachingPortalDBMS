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

import com.dbmsproject.ignite.tutorials.dao.StaffAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.StaffAttendance;
import com.dbmsproject.ignite.tutorials.model.TeacherAttendance;


@Component
public class StaffAttendanceDaoImpl implements StaffAttendanceDao{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	public void addStaffAttendance(StaffAttendance obj) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("insert into staffAttendance values(:StaffId,:Date,:isPresent)")
		.setParameter("StaffId", obj.getStaffId())
		.setParameter("Date",obj.getDate())
		.setParameter("isPresent", obj.getIsPresent())
		.executeUpdate();
	}
	
	@Transactional
	public ArrayList<StaffAttendance> getAttendance(Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query<StaffAttendance> query =session.createNativeQuery("select * from staffAttendance where date=:date order by(staffId)",StaffAttendance.class)
		.setParameter("date", date);
		ArrayList<StaffAttendance> result = (ArrayList<StaffAttendance>) query.getResultList();
		return result;
	}
	
	@Transactional
	public List<StaffAttendance> getAllAttendanceByStaffId(int staffId) {
		Session session = sessionFactory.getCurrentSession();
		Query<StaffAttendance> query = session.createNativeQuery("select * from staffAttendance where staffId=:staffId order by(date) desc",StaffAttendance.class)
				.setParameter("staffId",staffId);
		List<StaffAttendance> result = query.getResultList();
		return result;
	}
}

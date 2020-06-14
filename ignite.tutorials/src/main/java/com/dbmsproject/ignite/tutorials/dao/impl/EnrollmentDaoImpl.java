package com.dbmsproject.ignite.tutorials.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.EnrollmentDao;
import com.dbmsproject.ignite.tutorials.model.Enrollment;
import com.dbmsproject.ignite.tutorials.model.Exam;
import com.dbmsproject.ignite.tutorials.model.Student;

@Component
public class EnrollmentDaoImpl implements EnrollmentDao{
	
	@Autowired
	private SessionFactory sessionFactory;
    
    @Transactional
    public void addEnrollment(Enrollment enrollment) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into enrollment values(:TransactionNo,:JoinDate,:EndDate,:EnrolledBy,:CourseId,:RollNo,:BatchId)")
        .setParameter("TransactionNo",enrollment.getTransactionNo())
        .setParameter("JoinDate", enrollment.getJoinDate())
        .setParameter("EndDate", enrollment.getEndDate())
		.setParameter("EnrolledBy", enrollment.getEnrolledBy())
		.setParameter("BatchId", enrollment.getBatchId())
		.setParameter("CourseId", enrollment.getCourseId())
		.setParameter("RollNo", enrollment.getRollNo())
        .executeUpdate();
    }
    
    @Transactional
    public List<Student> getAllStudentsByCourseId(String courseId){
    	Session session = sessionFactory.getCurrentSession();
    	Query<Student> query = session.createNativeQuery("select student.* from student,enrollment where student.RollNo=enrollment.RollNo and enrollment.CourseId=:courseId", Student.class)
    			.setParameter("courseId", courseId);
    	List<Student> result = (List<Student>)(query.getResultList());
    	return result;
    }
    
    @Transactional
    public List<Student> getAllStudentsByCourseIdAndBatchIdAndDate(String courseId, int batchId, java.sql.Date date) {
    	Session session = sessionFactory.getCurrentSession();
    	Query<Student> query = session.createNativeQuery("select student.* from student,enrollment where student.RollNo = enrollment.RollNo and enrollment.CourseId=:courseId and enrollment.BatchId=:batchId and enrollment.JoinDate<=:date and enrollment.EndDate>=:date order by(student.rollNo)", Student.class)
    			.setParameter("courseId", courseId)
    			.setParameter("batchId", batchId)
    			.setParameter("date", date);
    	List<Student> result = (List<Student>)(query.getResultList());
    	return result;
    }
    
    @Transactional
    public List<Student> getAllStudentsByCourseIdAndBatchId(String courseId, int batchId){
    	Session session = sessionFactory.getCurrentSession();
    	Query<Student> query = session.createNativeQuery("select student.* from student,enrollment where student.RollNo = enrollment.RollNo and enrollment.CourseId = :courseId and enrollment.batchId = :batchId", Student.class)
    			.setParameter("courseId",courseId)
    			.setParameter("batchId", batchId);
    	List<Student> result = (List<Student>)(query.getResultList());
    	return result;
    }

	@Override
	public List<Enrollment> getAllEnrollments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Enrollment> query = session.createNativeQuery("select * from enrollment",Enrollment.class);
		List<Enrollment> result = (List<Enrollment>)(query.getResultList());
		return result;
	}

	@Override
	public Enrollment getEnrollmentsByRollCourseBatch(int rollNo, String courseId, int batchId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Enrollment> query = session.createNativeQuery("select * from enrollment where RollNo=:rollNo and CourseId=:courseId and BatchId=:batchId",Enrollment.class)
			.setParameter("rollNo",rollNo)
			.setParameter("courseId", courseId)
			.setParameter("batchId", batchId);
		Enrollment result = query.getSingleResult();
		return result;
	}

	@Override
	public void updateEnrollment(Enrollment enrollment) {
		sessionFactory.getCurrentSession().update(enrollment);
	}

	@Override
	public void deleteEnrollment(int rollNo, String courseId, int batchId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from enrollment where RollNo=:rollNo and CourseId=:courseId and BatchId=:batchId")
		.setParameter("rollNo",rollNo)
		.setParameter("courseId", courseId)
		.setParameter("batchId", batchId)
		.executeUpdate();
	}

	@Transactional
	public List<Student> getAllStudentsByCourseIdAndDate(String courseId, Date date) {
		Session session = sessionFactory.getCurrentSession();
    	Query<Student> query = session.createNativeQuery("select student.* from student,enrollment where student.RollNo = enrollment.RollNo and enrollment.CourseId=:courseId and enrollment.JoinDate<=:date and enrollment.EndDate>=:date", Student.class)
    			.setParameter("courseId", courseId)
    			.setParameter("date", date);
    	List<Student> result = (List<Student>)(query.getResultList());
    	return result;
	}

	@Transactional
	public List<Enrollment> getCoursesByRoll(int rollNo) {
		Session session = sessionFactory.getCurrentSession();
		Query<Enrollment> query = session.createNativeQuery("select * from enrollment where rollNo=:rollNo", Enrollment.class)
				.setParameter("rollNo", rollNo);
		List<Enrollment> result = query.getResultList();
		return result;
	}

}

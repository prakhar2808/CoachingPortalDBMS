package com.dbmsproject.ignite.tutorials.dao.impl;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.ExamDao;
import com.dbmsproject.ignite.tutorials.model.Enrollment;
import com.dbmsproject.ignite.tutorials.model.Exam;

@Component
public class ExamDaoImpl implements ExamDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Transactional
    public void addExam(Exam exam) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into exam values(:ExamId,:CourseId,:ExamName,:ExamDate,:StartTiming,:EndTiming)")
        .setParameter("ExamId", exam.getExamId())
        .setParameter("CourseId", exam.getCourseId())
        .setParameter("ExamName", exam.getExamName())
        .setParameter("ExamDate", exam.getExamDate())
        .setParameter("StartTiming", exam.getStartTiming())
        .setParameter("EndTiming", exam.getEndTiming())
        .executeUpdate();
    }
	 
	@Transactional
	public List<Exam> getAllExamsByCourseId(String courseId){
		Session session = sessionFactory.getCurrentSession();
		Query<Exam> query = session.createNativeQuery("select * from exam where CourseId=:courseId order by(examDate)", Exam.class)
		.setParameter("courseId", courseId);
		List<Exam> result = (List<Exam>)(query.getResultList());
		return result;
	}

	@Transactional
	public void deleteExam(String examId, String courseId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from exam where ExamId=:examId and CourseId=:courseId")
		.setParameter("examId", examId)
		.setParameter("courseId", courseId)
		.executeUpdate();
	}

	@Transactional
	public Exam getExam(String examId, String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Exam> query = session.createNativeQuery("select * from exam where ExamId=:examId and CourseId=:courseId", Exam.class)
				.setParameter("examId",examId)
				.setParameter("courseId",courseId);
		Exam result = query.getSingleResult();
		return result;
	}

	@Transactional
	public void updateExam(Exam exam) {
		sessionFactory.getCurrentSession().update(exam);
	}

	@Transactional
	public List<Exam> getAllExams() {
		Session session = sessionFactory.getCurrentSession();
		Query<Exam> query = session.createNativeQuery("select * from exam",Exam.class);
		List<Exam> result = query.getResultList();
		return result;
	}

	
}

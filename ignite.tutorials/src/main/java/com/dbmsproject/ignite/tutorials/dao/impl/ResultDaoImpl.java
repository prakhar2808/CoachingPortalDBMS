package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.ResultDao;
import com.dbmsproject.ignite.tutorials.model.Result;


@Component
public class ResultDaoImpl implements ResultDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Transactional
    public void addResult(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into result values(:ExamId,:CourseId,:ResultDeclarationDate,:StudentRank,:RollNo,:Marks)")
        .setParameter("ExamId", result.getExamId())
        .setParameter("CourseId", result.getCourseId())
        .setParameter("ResultDeclarationDate", result.getResultDeclarationDate())
        .setParameter("StudentRank", result.getStudentRank())
        .setParameter("RollNo", result.getRollNo())
        .setParameter("Marks", result.getMarks())
        .executeUpdate();
    }
	 
	 @Transactional
	 public List<Result> getResult(String examId, String courseId) {
		 Session session = sessionFactory.getCurrentSession();
		 Query<Result> query = session.createNativeQuery("select * from result where examId=:examId and courseId=:courseId order by(StudentRank)",Result.class)
		 .setParameter("examId", examId)
		 .setParameter("courseId", courseId);
		 List<Result> result = query.getResultList();
		 return result;
	 }

	@Transactional
	public Result getResultByRollNo(int rollNo, String courseId, String examId) {
		 Session session = sessionFactory.getCurrentSession();
		 Query<Result> query = session.createNativeQuery("select * from result where examId=:examId and courseId=:courseId and rollNo=:rollNo",Result.class)
		 .setParameter("examId", examId)
		 .setParameter("courseId", courseId)
		 .setParameter("rollNo", rollNo);
		 Result result = query.getSingleResult();
		 return result;	
	}

	@Transactional
	public void updateResult(Result result) {
		sessionFactory.getCurrentSession().update(result);
	}

	@Override
	public List<Result> getResultsByRollNo(int rollNo) {
		Session session = sessionFactory.getCurrentSession();
		Query<Result> query = session.createNativeQuery("select * from result where rollNo=:rollNo order by(resultDeclarationDate)", Result.class)
				.setParameter("rollNo", rollNo);
		List<Result> result = query.getResultList();
		return result;
	}
	
}

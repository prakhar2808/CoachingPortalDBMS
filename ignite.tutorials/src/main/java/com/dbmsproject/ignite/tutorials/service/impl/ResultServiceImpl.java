package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.ResultDao;
import com.dbmsproject.ignite.tutorials.model.Result;
import com.dbmsproject.ignite.tutorials.service.ResultService;

@Component
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultDao resultDao;
	
	@Transactional
	public void addResult(Result result) {
		resultDao.addResult(result);
	}

	@Transactional
	public List<Result> getResult(String examId, String courseId) {
		return resultDao.getResult(examId,courseId);
	}

	@Transactional
	public Result getResultByRollNo(int rollNo, String courseId, String examId) {
		return resultDao.getResultByRollNo(rollNo,courseId,examId);
	}

	@Transactional
	public void updateResult(Result result) {
		if(result == null)
			return;
		Result updated_result = resultDao.getResultByRollNo(result.getRollNo(), result.getCourseId(), result.getExamId());
		updated_result.setMarks(result.getMarks());
		updated_result.setStudentRank(result.getStudentRank());
		resultDao.updateResult(updated_result);
	}

	@Transactional
	public List<Result> getResultsByRollNo(int rollNo) {
		return resultDao.getResultsByRollNo(rollNo);
	}
}

package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Result;

@Component
public interface ResultService {
	public void addResult(Result result);

	public List<Result> getResult(String examId, String courseId);

	public Result getResultByRollNo(int rollNo, String courseId, String examId);

	public void updateResult(Result result);

	public List<Result> getResultsByRollNo(int rollNo);
}

package com.dbmsproject.ignite.tutorials.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Subject;

@Component
public interface SubjectDao {
	void addSubject(Subject subject);

	void deleteSubject(String courseId, String subjectName);

	List<Subject> getSubjects(String courseId);
}

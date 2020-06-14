package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Subject;

@Component
public interface SubjectService {
	public void addSubject(Subject subject);

	public List<Subject> getSubjects(String courseId);

	public void deleteSubject(String courseId, String subjectName);
}

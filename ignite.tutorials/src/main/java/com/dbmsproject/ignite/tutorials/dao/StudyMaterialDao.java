package com.dbmsproject.ignite.tutorials.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.StudyMaterial;

@Component
public interface StudyMaterialDao {
	public void addStudyMaterial(StudyMaterial studyMaterial);

	public List<StudyMaterial> getStudyMaterial(String courseId);

	public void deleteStudyMaterial(String courseId, String smId);
}

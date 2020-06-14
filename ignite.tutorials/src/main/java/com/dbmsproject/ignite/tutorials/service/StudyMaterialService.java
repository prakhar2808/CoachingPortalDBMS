package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.StudyMaterial;

@Component
public interface StudyMaterialService {
	public void addStudyMaterial(StudyMaterial studyMaterial);

	public List<StudyMaterial> getStudyMaterials(String courseId);

	public void deleteStudyMaterial(String courseId, String smId);
}

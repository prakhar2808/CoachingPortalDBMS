package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StudyMaterialDao;
import com.dbmsproject.ignite.tutorials.model.StudyMaterial;
import com.dbmsproject.ignite.tutorials.service.StudyMaterialService;

@Component
public class StudyMaterialServiceImpl implements StudyMaterialService{
	
	@Autowired
	StudyMaterialDao studyMaterialDao;
	
	public void addStudyMaterial(StudyMaterial studyMaterial) {
		studyMaterialDao.addStudyMaterial(studyMaterial);
	}

	@Transactional
	public List<StudyMaterial> getStudyMaterials(String courseId) {
		return studyMaterialDao.getStudyMaterial(courseId);
	}

	@Transactional
	public void deleteStudyMaterial(String courseId, String smId) {
		studyMaterialDao.deleteStudyMaterial(courseId,smId);
	}
}

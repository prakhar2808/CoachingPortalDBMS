package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StudyMaterialDao;
import com.dbmsproject.ignite.tutorials.model.StudyMaterial;

@Component
public class StudyMaterialDaoImpl implements StudyMaterialDao{
	
	@Autowired
	private SessionFactory sessionFactory;
    
    @Transactional
    public void addStudyMaterial(StudyMaterial studyMaterial) {
    	Session session = sessionFactory.getCurrentSession();
    	session.createNativeQuery("insert into studyMaterial values(:MaterialCode,:CourseId,:TopicName,:Level)")
    	.setParameter("MaterialCode", studyMaterial.getMaterialCode())
    	.setParameter("CourseId", studyMaterial.getCourseId())
    	.setParameter("TopicName",studyMaterial.getTopicName())
    	.setParameter("Level",studyMaterial.getLevel())
    	.executeUpdate();
    }

	@Transactional
	public List<StudyMaterial> getStudyMaterial(String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<StudyMaterial> query = session.createNativeQuery("select * from studyMaterial where courseId=:courseId order by(materialCode)",StudyMaterial.class)
				.setParameter("courseId", courseId);
		List<StudyMaterial> result = query.getResultList();
		return result;
	}

	@Transactional
	public void deleteStudyMaterial(String courseId, String smId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from studyMaterial where courseId=:courseId and materialCode=:smId")
		.setParameter("courseId",courseId)
		.setParameter("smId",smId)
		.executeUpdate();
	}

}

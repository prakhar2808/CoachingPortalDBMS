package com.dbmsproject.ignite.tutorials.service.impl;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbmsproject.ignite.tutorials.dao.AdminDao;
import com.dbmsproject.ignite.tutorials.dao.GuardianDao;
import com.dbmsproject.ignite.tutorials.dao.StudentDao;
import com.dbmsproject.ignite.tutorials.dao.TeacherDao;
import com.dbmsproject.ignite.tutorials.dao.StaffDao;

@Service
public class MyAdminDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private GuardianDao guardianDao;
	@Autowired
	private StaffDao staffDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		String prefix = username.substring(0, 3);		
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		User user = null;
		
		if(prefix.equals("ADM")){
			com.dbmsproject.ignite.tutorials.model.Admin admin = adminDao.getAdminByAdminId(Integer.parseInt(username.substring(3)));
			authorities.add(new SimpleGrantedAuthority(admin.getRole()));
			user = buildUserForAuthentication(username, admin.getPassword(), authorities);
		}
		
		if(prefix.equals("TEA")){
			com.dbmsproject.ignite.tutorials.model.Teacher teacher = teacherDao.getTeacherByTeacherId(Integer.parseInt(username.substring(3)));
			authorities.add(new SimpleGrantedAuthority(teacher.getRole()));
			user = buildUserForAuthentication(username, teacher.getPassword(), authorities);
		}
		
		if(prefix.equals("STU")){
			com.dbmsproject.ignite.tutorials.model.Student student = studentDao.getStudentByRollNo(Integer.parseInt(username.substring(3)));
			authorities.add(new SimpleGrantedAuthority(student.getRole()));
			user = buildUserForAuthentication(username, student.getPassword(), authorities);
		}
		
		if(prefix.equals("GUA")){
			com.dbmsproject.ignite.tutorials.model.Guardian guardian = guardianDao.getGuardianByGuardianId(Integer.parseInt(username.substring(3)));
			authorities.add(new SimpleGrantedAuthority(guardian.getRole()));
			user = buildUserForAuthentication(username, guardian.getPassword(), authorities);
		}
		
		if(prefix.equals("STA")){
			com.dbmsproject.ignite.tutorials.model.Staff staff = staffDao.getStaffByStaffId(Integer.parseInt(username.substring(3)));
			authorities.add(new SimpleGrantedAuthority(staff.getRole()));
			user = buildUserForAuthentication(username, staff.getPassword(), authorities);
		}
		
		return user;
	}

	private User buildUserForAuthentication(String username, String password,List<GrantedAuthority> authorities) {
		return new User(username, password, true,true, true, true, authorities);
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}


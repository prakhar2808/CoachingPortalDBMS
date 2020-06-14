package com.dbmsproject.ignite.tutorials.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbmsproject.ignite.tutorials.service.AdminService;
import com.dbmsproject.ignite.tutorials.service.GuardianService;
import com.dbmsproject.ignite.tutorials.service.StaffService;
import com.dbmsproject.ignite.tutorials.service.StudentService;
import com.dbmsproject.ignite.tutorials.service.TeacherService;

@Controller
public class MainController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private GuardianService guardianService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private AdminService adminService;
	
	@Transactional
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView helloPage() {

    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	System.out.println(encoder.encode("admin"));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail.getUsername().substring(0, 3));
			if(userDetail.getUsername().substring(0, 3).equals("STU"))
				return new ModelAndView("redirect:/student/home");
			if(userDetail.getUsername().substring(0, 3).equals("ADM"))
				return new ModelAndView("redirect:/admin");
			if(userDetail.getUsername().substring(0, 3).equals("GUA"))
				return new ModelAndView("redirect:/guardian/home");
			if(userDetail.getUsername().substring(0, 3).equals("STA"))
				return new ModelAndView("redirect:/staff/home");
			if(userDetail.getUsername().substring(0, 3).equals("TEA"))
				return new ModelAndView("redirect:/teacher/home");
			return new ModelAndView( "redirect:/welcome");
		}
		ModelAndView model = new ModelAndView("RootPage");
		model.addObject("title","Database Management System Project");
		model.addObject("msg","This is the default page");
		return model;
	}
	
	
	
	
	
	@Transactional
	@RequestMapping(value = {"/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {UserDetails userDetail = (UserDetails) auth.getPrincipal();
		System.out.println(userDetail.getUsername().substring(0, 3));
			if(userDetail.getUsername().substring(0, 3).equals("STU"))
				return new ModelAndView("redirect:/student/home");
			if(userDetail.getUsername().substring(0, 3).equals("ADM"))
				return new ModelAndView("redirect:/admin");
			if(userDetail.getUsername().substring(0, 3).equals("GUA"))
				return new ModelAndView("redirect:/guardian/home");
			if(userDetail.getUsername().substring(0, 3).equals("STA"))
				return new ModelAndView("redirect:/staff/home");
			if(userDetail.getUsername().substring(0, 3).equals("TEA"))
				return new ModelAndView("redirect:/teacher/home");
			ModelAndView model = new ModelAndView();
			model.addObject("title", "You are logged in now!");
			model.setViewName("Welcome");
			model.addObject("message", "Welcome "+userDetail.getUsername()+" !");
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView( "redirect:/welcome");
		}

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@Transactional
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
	
	@Transactional
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public StudentService getStudentService() {
		return studentService;
	}
	
	@Autowired(required=true)
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public GuardianService getGuardianService() {
		return guardianService;
	}
	
	@Autowired(required=true)
	public void setGuardianService(GuardianService guardianService) {
		this.guardianService = guardianService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}
	
	@Autowired(required=true)
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	@Autowired(required=true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	@Autowired(required=true)
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}

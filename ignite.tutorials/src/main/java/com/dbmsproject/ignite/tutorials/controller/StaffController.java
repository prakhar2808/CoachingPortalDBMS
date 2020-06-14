package com.dbmsproject.ignite.tutorials.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbmsproject.ignite.tutorials.model.Batch;
import com.dbmsproject.ignite.tutorials.model.Course;
import com.dbmsproject.ignite.tutorials.model.Enrollment;
import com.dbmsproject.ignite.tutorials.model.Exam;
import com.dbmsproject.ignite.tutorials.model.Guardian;
import com.dbmsproject.ignite.tutorials.model.Result;
import com.dbmsproject.ignite.tutorials.model.ResultWrapper;
import com.dbmsproject.ignite.tutorials.model.Staff;
import com.dbmsproject.ignite.tutorials.model.StaffAttendance;
import com.dbmsproject.ignite.tutorials.model.StaffAttendanceWrapper;
import com.dbmsproject.ignite.tutorials.model.Student;
import com.dbmsproject.ignite.tutorials.model.StudentAttendance;
import com.dbmsproject.ignite.tutorials.model.StudentAttendanceWrapper;
import com.dbmsproject.ignite.tutorials.model.StudyMaterial;
import com.dbmsproject.ignite.tutorials.model.Subject;
import com.dbmsproject.ignite.tutorials.model.Teacher;
import com.dbmsproject.ignite.tutorials.model.TeacherAttendance;
import com.dbmsproject.ignite.tutorials.model.TeacherAttendanceWrapper;
import com.dbmsproject.ignite.tutorials.model.Teaches;
import com.dbmsproject.ignite.tutorials.service.AdminService;
import com.dbmsproject.ignite.tutorials.service.BatchService;
import com.dbmsproject.ignite.tutorials.service.CourseService;
import com.dbmsproject.ignite.tutorials.service.EnrollmentService;
import com.dbmsproject.ignite.tutorials.service.ExamService;
import com.dbmsproject.ignite.tutorials.service.GuardianService;
import com.dbmsproject.ignite.tutorials.service.ResultService;
import com.dbmsproject.ignite.tutorials.service.StaffAttendanceService;
import com.dbmsproject.ignite.tutorials.service.StaffService;
import com.dbmsproject.ignite.tutorials.service.StudentAttendanceService;
import com.dbmsproject.ignite.tutorials.service.StudentService;
import com.dbmsproject.ignite.tutorials.service.StudyMaterialService;
import com.dbmsproject.ignite.tutorials.service.SubjectService;
import com.dbmsproject.ignite.tutorials.service.TeacherAttendanceService;
import com.dbmsproject.ignite.tutorials.service.TeacherService;
import com.dbmsproject.ignite.tutorials.service.TeachesService;

@Controller
public class StaffController {
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
	@Autowired
	private CourseService courseService;
	@Autowired
	private BatchService batchService;
	@Autowired
	private EnrollmentService enrollmentService;
	@Autowired
	private ExamService examService;
	@Autowired
	private ResultService resultService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeachesService teachesService;
	@Autowired
	private StudyMaterialService studyMaterialService;
	@Autowired
	private StudentAttendanceService studentAttendanceService;
	@Autowired
	private TeacherAttendanceService teacherAttendanceService;
	@Autowired
	private StaffAttendanceService staffAttendanceService;

	
	@RequestMapping("/staff/home")
	public ModelAndView staffHome() {
		ModelAndView model = new ModelAndView("staffHome");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		int staffId = Integer.parseInt(userDetail.getUsername().substring(3));
		Staff staff = staffService.getStaffByStaffId(staffId);
		model.addObject("staff",staff);
		return model;
	}
	
	@RequestMapping("/staff/attendance")
	public ModelAndView viewAttendance() {
		ModelAndView model = new ModelAndView("studentviewAttendance");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		int staffId = Integer.parseInt(userDetail.getUsername().substring(3));
		List<StaffAttendance> attList = staffAttendanceService.getAllAttendanceByStaffId(staffId);
		model.addObject("attList", attList);
		double P=0,T=attList.size();
		for(int i=0;i<T;i++) {
			if(attList.get(i).getIsPresent().equals("YES"))
				P++;
		}
		double percentage = (P/T)*100.0;
		model.addObject("percentage",(int)percentage);
		return model;
	}

	

	
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public GuardianService getGuardianService() {
		return guardianService;
	}
	public void setGuardianService(GuardianService guardianService) {
		this.guardianService = guardianService;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public TeacherAttendanceService getTeacherAttendanceService() {
		return teacherAttendanceService;
	}

	public void setTeacherAttendanceService(TeacherAttendanceService teacherAttendanceService) {
		this.teacherAttendanceService = teacherAttendanceService;
	}

	public StaffAttendanceService getStaffAttendanceService() {
		return staffAttendanceService;
	}

	public void setStaffAttendanceService(StaffAttendanceService staffAttendanceService) {
		this.staffAttendanceService = staffAttendanceService;
	}

}

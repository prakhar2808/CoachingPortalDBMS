package com.dbmsproject.ignite.tutorials.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.gson.Gson;

@Controller
public class GuardianController {
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
	
	//students - courses enrolled
	//students - exam results
	//students - attendance
	//students - exam results graph
	
	@RequestMapping("/guardian/home")
	public ModelAndView guardianHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Guardian guardian = guardianService.getGuardianByGuardianId(Integer.parseInt(userDetail.getUsername().substring(3)));
		ModelAndView model = new ModelAndView("guardianHome");
		model.addObject("guardian",guardian);
		List<Student> children = studentService.getStudentByGuardianId(guardian.getGuardianId());
		model.addObject("children",children);
		return model;
	}
	
	@RequestMapping("/guardian/view_results/{rollNo}")
	public ModelAndView viewChildResults(@PathVariable("rollNo") int rollNo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		int guardianId = Integer.parseInt(userDetail.getUsername().substring(3));
		Student student = studentService.getStudentByRollNo(rollNo);
		if(student.getGuardianId()!=guardianId) 
			return new ModelAndView("redirect:/guardian/home");
		ModelAndView model = new ModelAndView("viewChildResults");
		List<Result> resultList = resultService.getResultsByRollNo(rollNo);
		model.addObject("resultList",resultList);
		model.addObject("student",student);
		return model;
	}
	
	@RequestMapping("/guardian/view_attendance/{rollNo}")
	public ModelAndView viewChildAttendanceSelectCourse(@PathVariable("rollNo") int rollNo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		int guardianId = Integer.parseInt(userDetail.getUsername().substring(3));
		Student student = studentService.getStudentByRollNo(rollNo);
		if(student.getGuardianId()!=guardianId) 
			return new ModelAndView("redirect:/guardian/home");
		ModelAndView model = new ModelAndView("viewChildAttendanceSelectCourse");
		List<Enrollment> enrollmentList = enrollmentService.getCoursesByRoll(rollNo);
		List<Course> courseList = new ArrayList<>();
		for(int i=0;i<enrollmentList.size();i++)
			courseList.add(courseService.getCourseByCourseId(enrollmentList.get(i).getCourseId()));
		model.addObject("courseList",courseList);
		model.addObject("course",new Course());
		model.addObject("student",student);
		return model;
	}
	
	@RequestMapping(value = "/guardian/view_attendance/{rollNo}/list", method=RequestMethod.POST)
	public ModelAndView viewChildAttendance(@ModelAttribute("course") Course course,
			@PathVariable("rollNo") int rollNo) {
		Student student = studentService.getStudentByRollNo(rollNo);
		String courseId = course.getCourseId();
		List<StudentAttendance> attList = studentAttendanceService.getAttendanceByRollAndCourse(rollNo, courseId);
		ModelAndView model = new ModelAndView("viewChildAttendance");
		model.addObject("student",student);
		model.addObject("attList",attList);
		return model;
	}
	
	@RequestMapping("/guardian/view_assessment/{rollNo}")
	public ModelAndView viewChildAssessment(@PathVariable("rollNo") int rollNo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		int guardianId = Integer.parseInt(userDetail.getUsername().substring(3));
		Student student = studentService.getStudentByRollNo(rollNo);
		if(student.getGuardianId()!=guardianId) 
			return new ModelAndView("redirect:/guardian/home");
		ModelAndView model = new ModelAndView("viewAssessment");
		List<Result> resultList = resultService.getResultsByRollNo(rollNo);
		model.addObject("student",student);
		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		
		for(int i=0;i<resultList.size();i++) {
			map = new HashMap<Object,Object>(); map.put("label", resultList.get(i).getExamId()+" "+resultList.get(i).getCourseId()); map.put("y", resultList.get(i).getMarks()); 
			list.add(map);
		}
		String dataPoints = gsonObj.toJson(list);
		
		model.addObject("dataPoints",dataPoints);
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

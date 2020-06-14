package com.dbmsproject.ignite.tutorials.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class StudentController {
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
	
	//homepage - courses enrolled, roll no
	//view exams enrolled in
	//view results in exams
	//view attendance
	//sm available
	
	@RequestMapping("/student/home")
	public ModelAndView studentHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Student student = studentService.getStudentByRollNo(Integer.parseInt(userDetail.getUsername().substring(3)));
		ModelAndView model = new ModelAndView("studentHome");
		model.addObject("student",student);
		List<Enrollment> enrollmentList = enrollmentService.getCoursesByRoll(student.getRollNo());
		List<Course> courseList = new ArrayList<>();
		for(int i=0;i<enrollmentList.size();i++) {
			courseList.add(new Course());
			courseList.set(i, courseService.getCourseByCourseId(enrollmentList.get(i).getCourseId()));
		}
		model.addObject("courseList",courseList);
		Guardian guardian = guardianService.getGuardianByGuardianId(student.getGuardianId());
		model.addObject("guardian",guardian);
		return model;
	}
	
	@RequestMapping("/student/exams")
	public ModelAndView studentExams() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		int rollNo = Integer.parseInt(user.getUsername().substring(3));
		ModelAndView model = new ModelAndView("studentExams");
		List<Enrollment> enrollmentList = enrollmentService.getCoursesByRoll(rollNo);
		List<Exam> examList = new ArrayList<>();
		for(int i=0;i<enrollmentList.size();i++) {
			List<Exam> temp = examService.getAllExamsByCourseId(enrollmentList.get(i).getCourseId());
			examList.addAll(temp);
		}
		model.addObject("rollNo",rollNo);
		model.addObject("examList",examList);
		return model;
	}
	
	@RequestMapping("/student/exams/{examId}/{courseId}/{rollNo}")
	public ModelAndView studentResult(@PathVariable("examId") String examId,
			@PathVariable("courseId") String courseId,
			@PathVariable("rollNo") int rollNo) {
		ModelAndView model = new ModelAndView("studentExamsResult");
		Result result = resultService.getResultByRollNo(rollNo, courseId, examId);
		if(result == null)
			return new ModelAndView("ToBeAnnounced");
		model.addObject(result);
		return model;
	}
	
	@RequestMapping("/student/attendance")
	public ModelAndView studentAttendanceSelectCourse() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		int rollNo = Integer.parseInt(user.getUsername().substring(3));
		List<Enrollment> enrollmentList = enrollmentService.getCoursesByRoll(rollNo);
		List<Course> courseList = new ArrayList<>();
		for(int i=0;i<enrollmentList.size();i++)
			courseList.add(courseService.getCourseByCourseId(enrollmentList.get(i).getCourseId()));
		ModelAndView model = new ModelAndView("studentViewAttendanceSelectCourse");
		model.addObject("course", new Course());
		model.addObject("courseList",courseList);
		return model;
	}
	
	@RequestMapping("/student/attendance/view")
	public ModelAndView studentAttendance(@ModelAttribute("course") Course course) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		int rollNo = Integer.parseInt(user.getUsername().substring(3));
		ModelAndView model = new ModelAndView("studentviewAttendance");
		String courseId = course.getCourseId();
		List<StudentAttendance> attList= studentAttendanceService.getAttendanceByRollAndCourse(rollNo,courseId);
		int P=0,T = attList.size();
		for(int i=0;i<attList.size();i++) {
			if(attList.get(i).getIsPresent().equals("YES"))
				P++;
		}
		double percentage = ((double)P/(double)T)*100.00;
		model.addObject("percentage",(int)percentage);
		model.addObject("attList", attList);
		return model;
	}
	
	@RequestMapping("/student/sm")
	public ModelAndView studentSMSelectCourse() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		int rollNo = Integer.parseInt(user.getUsername().substring(3));
		List<Enrollment> enrollmentList = enrollmentService.getCoursesByRoll(rollNo);
		List<Course> courseList = new ArrayList<>();
		for(int i=0;i<enrollmentList.size();i++)
			courseList.add(courseService.getCourseByCourseId(enrollmentList.get(i).getCourseId()));
		ModelAndView model = new ModelAndView("studentViewSMSelectCourse");
		model.addObject("course", new Course());
		model.addObject("courseList",courseList);
		return model;
	}
	
	@RequestMapping("/student/sm/view")
	public ModelAndView studentSM(@ModelAttribute("course") Course course) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		int rollNo = Integer.parseInt(user.getUsername().substring(3));
		ModelAndView model = new ModelAndView("studentviewSM");
		String courseId = course.getCourseId();
		List<StudyMaterial> smList = studyMaterialService.getStudyMaterials(courseId);
		model.addObject("smList", smList);
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

package com.dbmsproject.ignite.tutorials.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AdminController {
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
	
	List<Character> genderList = new ArrayList<Character>();
	List<Integer> roomNos = new ArrayList<Integer>();
	
	AdminController(){
		genderList.add('M');
		genderList.add('F');
		roomNos.add(101);
		roomNos.add(102);
		roomNos.add(103);
		roomNos.add(201);
		roomNos.add(202);
		roomNos.add(203);
		roomNos.add(301);
		roomNos.add(302);
		roomNos.add(303);
	}
	
	@Transactional
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView checkadmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("admin");
			model.addObject("title","This is for admin only");
			model.addObject("message","You are a rightful admin");
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	
	@Transactional
	@RequestMapping(value="/admin/students_portal", method = RequestMethod.GET)
	public ModelAndView students_portal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalstudents");
			model.addObject("title","This is for admin only");
			model.addObject("message","Welcome to Students Portal");
			model.addObject("userList",studentService.getAllStudents());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/add_new_student", method = RequestMethod.GET)
	public ModelAndView add_new_student() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addStudent");
			model.addObject("title","This is for admin only");
			model.addObject("message","Add a new Student. If the guardian is not signed up, then add the guardian first.");
			model.addObject("student", new Student());
			List<Guardian> guardians = guardianService.getAllGuardians();
			model.addObject("guardians",guardians);
			model.addObject("genderList",genderList);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/add/STU", method = RequestMethod.POST)
	public ModelAndView save_student(@ModelAttribute("student") Student student, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/students_portal/add_new_student");
	    	}
	        if (null != student) {
	            studentService.addStudent(student);
	        }
	        return new ModelAndView("redirect:/admin/students_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/STU{RollNo}")
    public ModelAndView deleteStudent(@PathVariable("RollNo") int RollNo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        studentService.deleteStudent(RollNo);
	        return new ModelAndView( "redirect:/admin/students_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/STU{RollNo}")
	public ModelAndView editStudent(@PathVariable("RollNo") int RollNo) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Student student = studentService.getStudentByRollNo(RollNo);
			ModelAndView model = new ModelAndView("editStudent");
			model.addObject("student",student);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/save_changes/STU{RollNo}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("RollNo") int RollNo ,@ModelAttribute("student") Student student, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/STU{RollNo}");
	    	}
	    	studentService.updateStudent(student);
	    	return new ModelAndView("redirect:/admin/students_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	
	
	@Transactional
	@RequestMapping(value="/admin/teachers_portal", method = RequestMethod.GET)
	public ModelAndView teachers_portal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalteachers");
			model.addObject("title","This is for admin only");
			model.addObject("message","Welcome to Teachers Portal");
			model.addObject("userList",teacherService.getAllTeachers());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/teachers_portal/add_new_teacher", method = RequestMethod.GET)
	public ModelAndView add_new_teacher() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addTeacher");
			model.addObject("title","This is for admin only");
			model.addObject("message","Add a new Teacher");
			model.addObject("teacher", new Teacher());			
			model.addObject("genderList",genderList);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/teachers_portal/add/TEA", method = RequestMethod.POST)
	public ModelAndView save_teacher(@ModelAttribute("teacher") Teacher teacher, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/teachers_portal/add_new_teacher");
	    	}
	        if (null != teacher) {
	            teacherService.addTeacher(teacher);
	        }
	        return new ModelAndView("redirect:/admin/teachers_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/TEA{TeacherId}")
    public ModelAndView deleteTeacher(@PathVariable("TeacherId") int TeacherId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        teacherService.deleteTeacher(TeacherId);
	        return new ModelAndView( "redirect:/admin/teachers_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/TEA{TeacherId}")
	public ModelAndView editTeacher(@PathVariable("TeacherId") int TeacherId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Teacher teacher = teacherService.getTeacherByTeacherId(TeacherId);
			ModelAndView model = new ModelAndView("editTeacher");
			model.addObject("teacher",teacher);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/save_changes/TEA{TeacherId}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("TeacherId") int TeacherId ,@ModelAttribute("teacher") Teacher teacher, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/TEA{TeacherId}");
	    	}
	    	teacherService.updateTeacher(teacher);
	    	return new ModelAndView("redirect:/admin/teachers_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	
	
	@Transactional
	@RequestMapping(value="/admin/staff_portal", method = RequestMethod.GET)
	public ModelAndView staff_portal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalstaff");
			model.addObject("title","This is for admin only");
			model.addObject("message","Welcome to Staff Portal");
			model.addObject("userList",staffService.getAllStaffs());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/staff_portal/add_new_staff", method = RequestMethod.GET)
	public ModelAndView add_new_staff() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addStaff");
			model.addObject("title","This is for admin only");
			model.addObject("message","Add a new Staff");
			model.addObject("staff", new Staff());
			model.addObject("genderList",genderList);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/staff_portal/add/STA", method = RequestMethod.POST)
	public ModelAndView save_staff(@ModelAttribute("staff") Staff staff, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/staff_portal/add_new_staff");
	    	}
	        if (null != staff) {
	            staffService.addStaff(staff);
	        }
	        return new ModelAndView("redirect:/admin/staff_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/STA{StaffId}")
    public ModelAndView deleteStaff(@PathVariable("StaffId") int StaffId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        staffService.deleteStaff(StaffId);
	        return new ModelAndView( "redirect:/admin/staff_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/STA{StaffId}")
	public ModelAndView editStaff(@PathVariable("StaffId") int StaffId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Staff staff = staffService.getStaffByStaffId(StaffId);
			ModelAndView model = new ModelAndView("editStaff");
			model.addObject("staff",staff);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/save_changes/STA{StaffId}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("StaffId") int StaffId ,@ModelAttribute("staff") Staff staff, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/STA{StaffId}");
	    	}
	    	staffService.updateStaff(staff);
	    	return new ModelAndView("redirect:/admin/staff_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	
	
	@Transactional
	@RequestMapping(value="/admin/guardians_portal", method = RequestMethod.GET)
	public ModelAndView guardians_portal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalguardians");
			model.addObject("title","This is for admin only");
			model.addObject("message","Welcome to Guardians Portal");
			model.addObject("userList",guardianService.getAllGuardians());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/guardians_portal/add_new_guardian", method = RequestMethod.GET)
	public ModelAndView add_new_guardian() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addGuardian");
			model.addObject("title","This is for admin only");
			model.addObject("message","Add a new Guardian");
			model.addObject("guardian", new Guardian());
			model.addObject("genderList",genderList);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/guardians_portal/add/GUA", method = RequestMethod.POST)
	public ModelAndView save_guardian(@ModelAttribute("guardian") Guardian guardian, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/guardians_portal/add_new_guardian");
	    	}
	        if (null != guardian) {
	            guardianService.addGuardian(guardian);
	        }
	        return new ModelAndView("redirect:/admin/guardians_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/GUA{GuardianId}")
    public ModelAndView deleteGuardian(@PathVariable("GuardianId") int GuardianId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        guardianService.deleteGuardian(GuardianId);
	        return new ModelAndView( "redirect:/admin/guardians_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/GUA{GuardianId}")
	public ModelAndView editGuardian(@PathVariable("GuardianId") int GuardianId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Guardian guardian = guardianService.getGuardianByGuardianId(GuardianId);
			ModelAndView model = new ModelAndView("editGuardian");
			model.addObject("guardian",guardian);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/save_changes/GUA{GuardianId}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("GuardianId") int GuardianId ,@ModelAttribute("guardian") Guardian guardian, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/GUA{GuardianId}");
	    	}
	    	guardianService.updateGuardian(guardian);
	    	return new ModelAndView("redirect:/admin/guardians_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/courses_portal", method = RequestMethod.GET)
	public ModelAndView show_courses() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalcourses");
			model.addObject("courseList",courseService.getAllCourses());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/addCourse")
	public ModelAndView newCourse() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addCourse");
		    model.addObject("course", new Course());
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}

	
	@Transactional
	@RequestMapping(value="/admin/courses_portal/addCourse/save", method = RequestMethod.POST)
	public ModelAndView save_course(@ModelAttribute("course") Course course, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/courses_portal/addCourse");
	    	}
	        if (null != course) {
	            courseService.addCourse(course);
	        }
	        return new ModelAndView("redirect:/admin/courses_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/COU{CourseId}")
    public ModelAndView deleteCourse(@PathVariable("CourseId") String CourseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        courseService.deleteCourse(CourseId);
	        return new ModelAndView( "redirect:/admin/courses_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/COU{CourseId}")
	public ModelAndView editCourse(@PathVariable("CourseId") String CourseId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Course course = courseService.getCourseByCourseId(CourseId);
			ModelAndView model = new ModelAndView("editCourse");
			model.addObject("course",course);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/save_changes/COU{CourseId}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("CourseId") String CourseId ,@ModelAttribute("course") Course course, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/COU{CourseId}");
	    	}
	    	courseService.updateCourse(course);
	    	return new ModelAndView("redirect:/admin/courses_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("admin/courses_portal/view_batches/COU{CourseId}")
	public ModelAndView viewBatches(@PathVariable("CourseId") String CourseId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			List<Batch> batchList = batchService.getAllBatchesByCourseId(CourseId);
			ModelAndView model = new ModelAndView("view_batches");
			model.addObject("batchList",batchList);
			model.addObject("courseId",CourseId);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/addBatch/CourseId={CourseId}")
	public ModelAndView addBatch(@PathVariable("CourseId") String CourseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addBatch");
			Batch batch = new Batch();
			model.addObject("batch",batch);
			model.addObject("roomNos",roomNos);
			model.addObject("course",CourseId);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/courses_portal/addBatch/save{courseId}", method = RequestMethod.POST)
	public ModelAndView save_course(@ModelAttribute("batch") Batch batch, 
			@PathVariable("courseId") String courseId, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/courses_portal/addBatch/CourseId="+batch.getCourseId());
	    	}
	        if (null != batch) {
	        	batch.setCourseId(courseId);
	        	List<Batch> batchList = batchService.getAllBatches();
	        	for(int i=0;i<batchList.size();i++) {
	        		Batch another = batchList.get(i);
	        		if(another.getRoomNo() != batch.getRoomNo())
	        			continue;
	        		if(batch.getEndTiming().toLocalTime().isAfter(another.getStartTiming().toLocalTime()) && batch.getEndTiming().toLocalTime().isBefore(another.getEndTiming().toLocalTime()))
	        			return new ModelAndView( "redirect:/admin/courses_portal/addBatch/CourseId="+batch.getCourseId());
	        		if(batch.getStartTiming().toLocalTime().isBefore(another.getEndTiming().toLocalTime()) && batch.getStartTiming().toLocalTime().isAfter(another.getStartTiming().toLocalTime()))
	        			return new ModelAndView( "redirect:/admin/courses_portal/addBatch/CourseId="+batch.getCourseId());
	        	}
	            batchService.addBatch(batch);
	        }
	        return new ModelAndView("redirect:/admin/courses_portal/view_batches/COU"+courseId);
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/courses_portal/view_batches/delete/COU{courseId}/BAT{batchId}")
    public ModelAndView deleteBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") int batchId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        batchService.deleteBatch(batchId, courseId);
	        return new ModelAndView( "redirect:/admin/courses_portal/view_batches/COU"+courseId);
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/COU{courseId}/BAT{batchId}")
	public ModelAndView editBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") int batchId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Batch batch = batchService.getBatchByBatchIdAndCourseId(batchId,courseId);
			ModelAndView model = new ModelAndView("editBatch");
			model.addObject("batch",batch);
			model.addObject("courseId",courseId);
			model.addObject("roomNos",roomNos);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "admin/edit/COU{courseId}/save_changes/BAT{batchId}", method = RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable("batchId") int batchId, @ModelAttribute("batch") Batch batch, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/COU{courseId}/BAT{batchId}");
	    	}
	    	List<Batch> batchList = batchService.getAllBatches();
        	for(int i=0;i<batchList.size();i++) {
        		Batch another = batchList.get(i);
        		if(batch.getBatchId()==another.getBatchId() && batch.getCourseId().equals(another.getCourseId()))
        			continue;
        		if(another.getRoomNo() != batch.getRoomNo())
        			continue;
        		if(batch.getEndTiming().toLocalTime().isAfter(another.getStartTiming().toLocalTime()) && batch.getEndTiming().toLocalTime().isBefore(another.getEndTiming().toLocalTime())) 
        			return new ModelAndView( "redirect:/admin/edit/COU{courseId}/BAT{batchId}");
    			else if(batch.getStartTiming().toLocalTime().isBefore(another.getEndTiming().toLocalTime()) && batch.getStartTiming().toLocalTime().isAfter(another.getStartTiming().toLocalTime()))
        			return new ModelAndView( "redirect:/admin/edit/COU{courseId}/BAT{batchId}");
        		}
	    	batchService.updateBatch(batch);
	        return new ModelAndView( "redirect:/admin/courses_portal/view_batches/COU"+batch.getCourseId());
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_batches/view_studentsInBatch/COU{CourseId}/BAT{BatchId}")
	public ModelAndView viewStudentsInThisBatch(@PathVariable("CourseId") String CourseId,
			@PathVariable("BatchId") int BatchId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			List<Student> studentList = enrollmentService.getAllStudentsByCourseIdAndBatchId(CourseId, BatchId);
			System.out.println(studentList.size());
			ModelAndView model = new ModelAndView("view_studentsInThisBatch");
			model.addObject("studentList",studentList);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/enrollments_portal", method = RequestMethod.GET)
	public ModelAndView show_enrollments() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("portalenrollments");
			model.addObject("enrollmentList",enrollmentService.getAllEnrollments());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/enrollments_portal/addEnrollment/1")
	public ModelAndView newEnrollment() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addEnrollment");
		    model.addObject("enrollment", new Enrollment());
		    List<Course> courseList = courseService.getAllCourses();
		    model.addObject("courseList", courseList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/enrollments_portal/addEnrollment/2", method=RequestMethod.POST)
	public ModelAndView newEnrollmentChooseBatch(@ModelAttribute("enrollment") Enrollment enrollment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addEnrollmentChooseBatch");
		    model.addObject("enrollment", enrollment);
		    List<Batch> batchList = batchService.getAllBatchesByCourseId(enrollment.getCourseId());
		    model.addObject("batchList",batchList);
		    List<Staff> staffList = staffService.getAllStaffs();
		    model.addObject("staffList",staffList);
		    model.addObject("courseId",enrollment.getCourseId());
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/enrollments_portal/addEnrollment/course={courseId}/save", method = RequestMethod.POST)
	public ModelAndView save_course(@ModelAttribute("enrollment") Enrollment enrollment,
			@PathVariable("courseId") String courseId, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView("redirect:/admin/enrollments_portal/addEnrollment/1");
	    	}
	        if (null != enrollment) {
	        	enrollment.setCourseId(courseId);
	        	if(enrollment.getJoinDate().after(enrollment.getEndDate()))
	        		return new ModelAndView("redirect:/admin/enrollments_portal/addEnrollment/1");
	        	List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
	        	for(int i=0;i<enrollmentList.size();i++) {
	        		Enrollment another = enrollmentList.get(i);
	        		if(another.getRollNo() == enrollment.getRollNo() && another.getCourseId().equals(enrollment.getCourseId()))
	        			return new ModelAndView("redirect:/admin/enrollments_portal/addEnrollment/1");
	        	}
	            enrollmentService.addEnrollment(enrollment);
	        }
	        return new ModelAndView("redirect:/admin/enrollments_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("admin/edit/ENR/Roll={rollNo}/Course={courseId}/Batch={batchId}")
	public ModelAndView editEnrollment(@PathVariable("rollNo") int rollNo,
			@PathVariable("courseId") String courseId,
			@PathVariable("batchId") int batchId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Enrollment enrollment = enrollmentService.getEnrollmentsByRollCourseBatch(rollNo,courseId,batchId);
			ModelAndView model = new ModelAndView("editEnrollment");
			model.addObject("enrollment",enrollment);
			model.addObject("staffList",staffService.getAllStaffs());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "/admin/edit/ENR/Roll={rollNo}/Course={courseId}/save_changes/ENR/Batch={batchId}", method = RequestMethod.POST)
	public ModelAndView saveChangesEnrollment(@PathVariable("rollNo") int rollNo,
			@PathVariable("courseId") String courseId,
			@PathVariable("batchId") int batchId,
			@ModelAttribute("enrollment") Enrollment enrollment, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/ENR/Roll=${rollNo}/Course=${courseId}/Batch=${batchId}");
	    	}
	    	if(enrollment.getJoinDate().after(enrollment.getEndDate()))
	    		return new ModelAndView( "redirect:/admin/edit/ENR/Roll=${rollNo}/Course=${courseId}/Batch=${batchId}");
        	List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
	    	enrollmentService.updateEnrollment(enrollment);
	    	return new ModelAndView("redirect:/admin/enrollments_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/ENR/Roll={rollNo}/Course={courseId}/Batch={batchId}")
    public ModelAndView deleteEnrollment(@PathVariable("rollNo") int rollNo,
			@PathVariable("courseId") String courseId,
			@PathVariable("batchId") int batchId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        enrollmentService.deleteEnrollment(rollNo,courseId,batchId);
	        return new ModelAndView( "redirect:/admin/enrollments_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
	@Transactional
	@RequestMapping("/admin/exams_portal")
	public ModelAndView exams_portal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("portalexams");
		    List<Exam> examList = examService.getAllExams();
		    model.addObject("examList", examList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/exams_portal/addExam")
	public ModelAndView newExam() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addExam");
		    model.addObject("exam", new Exam());
		    List<Course> courseList = courseService.getAllCourses();
		    model.addObject("courseList", courseList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/exams_portal/addExam/save", method = RequestMethod.POST)
	public ModelAndView save_exam(@ModelAttribute("exam") Exam exam, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/exams_portal/addExam");
	    	}
	        if (null != exam) {
	        	if(exam.getStartTiming().toLocalTime().isAfter(exam.getEndTiming().toLocalTime()))
	        		return new ModelAndView( "redirect:/admin/exams_portal/addExam");
	            examService.addExam(exam);
	        }
	        return new ModelAndView("redirect:/admin/exams_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("admin/delete/EXAM{examId}/COU{courseId}")
    public ModelAndView deleteExam(@PathVariable("courseId") String courseId, @PathVariable("examId") String examId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        examService.deleteExam(examId,courseId);
	        return new ModelAndView( "redirect:/admin/exams_portal");
		}
		return new ModelAndView( "redirect:/login");
    }
	
    @RequestMapping("admin/edit/EXAM{examId}/COU{courseId}")
	public ModelAndView editExam(@PathVariable("courseId") String courseId, @PathVariable("examId") String examId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Exam exam = examService.getExam(examId,courseId);
			ModelAndView model = new ModelAndView("editExam");
			model.addObject("exam",exam);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping(value = "/admin/edit/EXAM{examId}/save_changes/COU{courseId}", method = RequestMethod.POST)
	public ModelAndView saveChangesExam(@PathVariable("courseId") String courseId ,
			@PathVariable("examId") String examId,
			@ModelAttribute("exam") Exam exam, BindingResult result){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/edit/EXAM{examId}/COU{courseId}");
	    	}
        	if(exam.getStartTiming().toLocalTime().isAfter(exam.getEndTiming().toLocalTime()))
        		return new ModelAndView( "redirect:/admin/edit/EXAM{examId}/COU{courseId}");
	    	examService.updateExam(exam);
	    	return new ModelAndView("redirect:/admin/exams_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/exams_portal/view_students/COU{courseId}/EXAM{examId}")
	public ModelAndView view_students_giving_exam(@PathVariable("courseId") String courseId,
			@PathVariable("examId") String examId) {
		ModelAndView model = new ModelAndView("viewStudentsGivingExam");
		Date date = examService.getExam(examId, courseId).getExamDate();
		List<Student> studentList = enrollmentService.getAllStudentsByCourseIdAndDate(courseId, date);
		model.addObject("studentList",studentList);
		return model;
	}
	
	
	@Transactional
	@RequestMapping("/admin/exams_portal/addResult")
	public ModelAndView newResult() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addResult");
		    model.addObject("result", new Result());
		    List<Course> courseList = courseService.getAllCourses();
		    model.addObject("courseList", courseList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/exams_portal/addResult/2", method=RequestMethod.POST)
	public ModelAndView newResultCont(@ModelAttribute("result") Result result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addResultCont");
		    model.addObject("result", result);
		    List<Exam> examList = examService.getAllExamsByCourseId(result.getCourseId());
		    model.addObject("examList",examList);
		    model.addObject("CourseId",result.getCourseId());
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/exams_portal/addResult/3/course={courseId}", method=RequestMethod.POST)
	public ModelAndView newResultCont2(@ModelAttribute("result") Result result,
			@PathVariable("courseId") String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addResultContNext");
		    model.addObject("result", result);
		    List<Exam> examList = examService.getAllExamsByCourseId(result.getCourseId());
		    model.addObject("examList",examList);
		    model.addObject("CourseId",result.getCourseId());
		    
		    String examId = result.getExamId();
		    ResultWrapper wrapper = new ResultWrapper();
		    Exam exam = examService.getExam(examId, courseId);
		    List<Student> studentList = enrollmentService.getAllStudentsByCourseIdAndDate(courseId, exam.getExamDate());
		    ArrayList<Result> resultList = new ArrayList<>();
		    for(int i=0;i<studentList.size();i++) {
		    	resultList.add(new Result());
		    	resultList.get(i).setCourseId(courseId);
		    	resultList.get(i).setExamId(examId);
		    	resultList.get(i).setRollNo(studentList.get(i).getRollNo());
		    	resultList.get(i).setResultDeclarationDate(result.getResultDeclarationDate());
		    }
		    wrapper.setResult(resultList);
		    model.addObject("wrapper",wrapper);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/exams_portal/addResult/save", method = RequestMethod.POST)
	public ModelAndView save_result(@ModelAttribute("wrapper") ResultWrapper wrapper,BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView("redirect:/admin/enrollments_portal/addResult");
	    	}
	    	ArrayList<Result> resultList = wrapper.getResult();
	    	for(int i=0;i<resultList.size();i++)
	    		resultService.addResult(resultList.get(i));
	        return new ModelAndView("redirect:/admin/exams_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/exams_portal/view_results/COU{courseId}/EXAM{examId}")
	public ModelAndView view_results_this_exam(@PathVariable("courseId") String courseId,
			@PathVariable("examId") String examId) {
		ModelAndView model = new ModelAndView("viewResultsThisExam");
		List<Result> resultList = resultService.getResult(examId,courseId);
		if(resultList == null)
			return new ModelAndView("ToBeAnnounced");
		model.addObject("resultList",resultList);
		return model;
	}
	
	@RequestMapping("/admin/edit/RES/Roll={rollNo}/Course={courseId}/Exam={examId}")
	public ModelAndView edit_result(@PathVariable("rollNo") int rollNo, 
			@PathVariable("courseId") String courseId,
			@PathVariable("examId") String examId) {
		ModelAndView model = new ModelAndView("editResult");
		Result result = resultService.getResultByRollNo(rollNo, courseId, examId);
		model.addObject("result",result);
		return model;
	}
	
	@RequestMapping(value = "/admin/edit/RES/Roll={rollNo}/Course={courseId}/save_changes/{examId}", method = RequestMethod.POST)
	public ModelAndView save_edit_result(@ModelAttribute("result") Result result_db, 
			@PathVariable("rollNo") int rollNo,
			@PathVariable("courseId") String courseId,
			@PathVariable("examId") String examId, BindingResult result) {
		if(result.hasErrors())
			return new ModelAndView("redirect:/admin/edit/RES/Roll={rollNo}/Course={courseId}/Exam={examId}");
		resultService.updateResult(result_db);
		return new ModelAndView("redirect:/admin/exams_portal/view_results/COU{courseId}/EXAM{examId}");
	}
	@RequestMapping("/admin/courses_portal/addSubject/CourseId={courseId}")
	public ModelAndView newSubjectInCourse(@PathVariable("courseId") String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addSubject");
		    Subject subject = new Subject();
		    subject.setCourseId(courseId);
		    model.addObject("subject", subject);
		    model.addObject("courseId",courseId);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/courses_portal/addSubject/CourseId={courseId}/save", method = RequestMethod.POST)
	public ModelAndView save_subject(@ModelAttribute("subject") Subject subject, 
			@PathVariable("courseId") String courseId, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/courses_portal/addSubject/CourseId={courseId}");
	    	}
	        if (null != subject) {
	            subjectService.addSubject(subject);
	        }
	        return new ModelAndView("redirect:/admin/courses_portal/view_subjects/COU"+courseId);
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_subjects/COU{courseId}")
	public ModelAndView view_subjects_in_course(@PathVariable("courseId") String courseId) {
		ModelAndView model = new ModelAndView("viewSubjectsInCourse");
		List<Subject> subList = subjectService.getSubjects(courseId);
		model.addObject("subList",subList);
		return model;
	}
	
	@Transactional
	@RequestMapping("/admin/courses_portal/view_subjects/delete/COU{courseId}/SUB{subjectName}")
    public ModelAndView deleteSubject(@PathVariable("courseId") String courseId, @PathVariable("subjectName") String subjectName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	        subjectService.deleteSubject(courseId, subjectName);
	        return new ModelAndView( "redirect:/admin/courses_portal/view_subjects/COU"+courseId);
		}
		return new ModelAndView( "redirect:/login");
    }
	
	@Transactional
	@RequestMapping("/admin/courses_portal/addTeacherToCourse")
	public ModelAndView newTeaches() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addTeacherToCourse");
		    model.addObject("teaches", new Teaches());
		    List<Course> courseList = courseService.getAllCourses();
		    model.addObject("courseList", courseList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/courses_portal/addTeacherToCourse/2", method=RequestMethod.POST)
	public ModelAndView newTeachesCont(@ModelAttribute("teaches") Teaches teaches) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addTeacherToCourseCont");
		    model.addObject("teaches", teaches);
		    List<Batch> batchList = batchService.getAllBatchesByCourseId(teaches.getCourseId());
		    model.addObject("batchList",batchList);
		    List<Teacher> teacherList = teacherService.getAllTeachers();
		    model.addObject("teacherList",teacherList);
		    model.addObject("courseId",teaches.getCourseId());
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/courses_portal/addTeacherToCourse/course={courseId}/save", method = RequestMethod.POST)
	public ModelAndView save_teacherToCourse(@ModelAttribute("teaches") Teaches teaches,
			@PathVariable("courseId") String courseId, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView("redirect:/admin/courses_portal/addTeacherToCourse");
	    	}
	        if (null != teaches) {
	        	teaches.setCourseId(courseId);
	            teachesService.addTeaches(teaches);
	        }
	        return new ModelAndView("redirect:/admin/courses_portal/view_batches/view_teachersInBatch/COU"+teaches.getCourseId()+"/BAT"+teaches.getBatchId());
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_batches/view_teachersInBatch/COU{CourseId}/BAT{BatchId}")
	public ModelAndView viewTeachersInThisBatch(@PathVariable("CourseId") String courseId,
			@PathVariable("BatchId") int batchId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			List<Teaches> teachesList = teachesService.getTeachers(courseId, batchId);
			List<Teacher> teacherList = new ArrayList<>();
			for(int i=0;i<teachesList.size();i++) {
				teacherList.add(new Teacher());
				teacherList.set(i,teacherService.getTeacherByTeacherId(teachesList.get(i).getTeacherId()));
			}
			ModelAndView model = new ModelAndView("view_teachersInThisBatch");
			model.addObject("teacherList",teacherList);
			model.addObject("courseId",courseId);
			model.addObject("batchId",batchId);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_batches/view_teachersInBatch/COU{courseId}/delete/TEA{teacherId}/BAT{batchId}")
	public ModelAndView deleteTeaches(@PathVariable("courseId") String courseId,
			@PathVariable("teacherId") int teacherId,
			@PathVariable("batchId") int batchId) {
		teachesService.deleteTeaches(courseId,teacherId,batchId);
		return new ModelAndView("redirect:/admin/courses_portal/view_batches/view_teachersInBatch/COU"+courseId+"/BAT"+Integer.toString(batchId));
	}
	
	@RequestMapping("/admin/courses_portal/addStudyMaterial/COU{courseId}")
	public ModelAndView newStudyMaterialInCourse(@PathVariable("courseId") String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    ModelAndView model = new ModelAndView("addStudyMaterial");
		    StudyMaterial sm = new StudyMaterial();
		    sm.setCourseId(courseId);
		    model.addObject("studyMaterial", sm);
		    List<Integer> levelList = new ArrayList<Integer>();
		    for(int i=1;i<=6;i++)
		    	levelList.add(i);
		    model.addObject("levelList",levelList);
		    return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/courses_portal/addStudyMaterial/save", method = RequestMethod.POST)
	public ModelAndView save_study_material(@ModelAttribute("studyMaterial") StudyMaterial studyMaterial,BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	if(result.hasErrors()) {
	    		return new ModelAndView( "redirect:/admin/courses_portal/addStudyMaterial");
	    	}
	        if (null != studyMaterial) {
	            studyMaterialService.addStudyMaterial(studyMaterial);
	        }
	        return new ModelAndView("redirect:/admin/courses_portal/view_sm/COU"+studyMaterial.getCourseId());
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_sm/COU{CourseId}")
	public ModelAndView viewSMInThisCourse(@PathVariable("CourseId") String courseId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			List<StudyMaterial> smList= studyMaterialService.getStudyMaterials(courseId);
			ModelAndView model = new ModelAndView("viewSM");
			model.addObject("smList",smList);
			model.addObject("courseId",courseId);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@RequestMapping("/admin/courses_portal/view_sm/delete/COU{courseId}/SM{smId}")
	public ModelAndView deleteSMinThisCourse(@PathVariable("courseId") String courseId,
			@PathVariable("smId") String smId){
		studyMaterialService.deleteStudyMaterial(courseId,smId);
		return new ModelAndView("redirect:/admin/courses_portal/view_sm/COU"+courseId);
	}
	
	@RequestMapping(value="/admin/attendance_portal")
	public ModelAndView attendancePortal() {
		ModelAndView model = new ModelAndView("portalattendance");
		return model;
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/addAttendanceSelectCourse", method = RequestMethod.GET)
	public ModelAndView addAttendance1() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addAttendanceSelectCourse");
			List<Course> courseList = courseService.getAllCourses();
			model.addObject("courseList",courseList);
			model.addObject("course",new Course());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/addAttendanceSelectBatch", method = RequestMethod.POST)
	public ModelAndView addAttendance2(@ModelAttribute("course") Course course) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addAttendanceSelectBatch");
			List<Batch> batchList = batchService.getAllBatchesByCourseId(course.getCourseId());
			model.addObject("batchList",batchList);
			model.addObject("courseId",course.getCourseId());
			model.addObject("studentAttendance",new StudentAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/students_portal/addAttendance/{courseId}", method=RequestMethod.POST)
	public ModelAndView addAttendanceForEach(@ModelAttribute("studentAttendance") StudentAttendance obj,
			@PathVariable("courseId") String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				ModelAndView model = new ModelAndView("addAttendanceAllStudents");
				int batchId = obj.getBatchId();
				List<Student> studentList = enrollmentService.getAllStudentsByCourseIdAndBatchIdAndDate(courseId,batchId,obj.getDate());
				ArrayList<StudentAttendance> attendanceList = new ArrayList<StudentAttendance>();
				for(int i=0;i<studentList.size();i++){
					attendanceList.add(new StudentAttendance());
					attendanceList.get(i).setBatchId(batchId);
					attendanceList.get(i).setCourseId(courseId);
					attendanceList.get(i).setRollNo(studentList.get(i).getRollNo());
					attendanceList.get(i).setDate(obj.getDate());
				}
				StudentAttendanceWrapper wrapper = new StudentAttendanceWrapper();
				wrapper.setStudentAttendance(attendanceList);
				model.addObject("studentAttendanceWrapper",wrapper);
				ArrayList<String> yesOrNo = new ArrayList<>();
				yesOrNo.add("YES");
				yesOrNo.add("NO");
				yesOrNo.add("N/A");
				model.addObject("yesOrNo",yesOrNo);
				return model;
			}
			return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/students_portal/saveAttendance", method=RequestMethod.POST)
	public ModelAndView saveAttendanceStudents(@ModelAttribute("wrapper") StudentAttendanceWrapper wrapper) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ArrayList<StudentAttendance> attendanceList = wrapper.getStudentAttendance();
			for(int i=0;i<attendanceList.size();i++)
				studentAttendanceService.addStudentAttendance(attendanceList.get(i));
			return new ModelAndView("redirect:/admin/attendance_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/viewAttendanceSelectCourse", method = RequestMethod.GET)
	public ModelAndView viewAttendance1() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("viewAttendanceSelectCourse");
			List<Course> courseList = courseService.getAllCourses();
			model.addObject("courseList",courseList);
			model.addObject("course",new Course());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/students_portal/viewAttendanceSelectBatch", method = RequestMethod.POST)
	public ModelAndView viewAttendance2(@ModelAttribute("course") Course course) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("viewAttendanceSelectBatch");
			List<Batch> batchList = batchService.getAllBatchesByCourseId(course.getCourseId());
			model.addObject("batchList",batchList);
			model.addObject("courseId",course.getCourseId());
			model.addObject("studentAttendance",new StudentAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/students_portal/viewAttendance/{courseId}", method=RequestMethod.POST)
	public ModelAndView viewAttendanceForEach(@ModelAttribute("studentAttendance") StudentAttendance obj,
			@PathVariable("courseId") String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				ModelAndView model = new ModelAndView("viewAttendanceAllStudents");
				int batchId = obj.getBatchId();
				ArrayList<StudentAttendance> attendanceList = studentAttendanceService.getAttendance(courseId,batchId,obj.getDate());
				StudentAttendanceWrapper wrapper = new StudentAttendanceWrapper();
				wrapper.setStudentAttendance(attendanceList);
				model.addObject("studentAttendanceWrapper",wrapper);
				return model;
			}
			return new ModelAndView( "redirect:/login");
	}
	
	
	
	@Transactional
	@RequestMapping("/admin/teachers_portal/addAttendance")
	public ModelAndView addAttendanceTeachers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addTeacherAttendance");
			model.addObject("teacherAttendance",new TeacherAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/teachers_portal/addAttendance/List", method=RequestMethod.POST)
	public ModelAndView addAttendanceTeachersList(@ModelAttribute("teacherAttendance") TeacherAttendance obj) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
				ModelAndView model = new ModelAndView("addTeacherAttendanceList");
				TeacherAttendanceWrapper wrapper = new TeacherAttendanceWrapper();
				List<Teacher> teacherList = teacherService.getAllTeachers();
				ArrayList<TeacherAttendance> teacherAttendanceList = new ArrayList<>();
				for(int i=0;i<teacherList.size();i++) {
					teacherAttendanceList.add(new TeacherAttendance());
					teacherAttendanceList.get(i).setDate(obj.getDate());
					teacherAttendanceList.get(i).setTeacherId(teacherList.get(i).getTeacherId());
				}
				wrapper.setTeacherAttendance(teacherAttendanceList);
				model.addObject("wrapper",wrapper);
				ArrayList<String> yesOrNo = new ArrayList<>();
				yesOrNo.add("YES");
				yesOrNo.add("NO");
				yesOrNo.add("N/A");
				model.addObject("yesOrNo",yesOrNo);
				return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teachers_portal/saveAttendance", method=RequestMethod.POST)
	public ModelAndView saveAttendanceTeachers(@ModelAttribute("wrapper") TeacherAttendanceWrapper wrapper) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ArrayList<TeacherAttendance> attendanceList = wrapper.getTeacherAttendance();
			for(int i=0;i<attendanceList.size();i++)
				teacherAttendanceService.addTeacherAttendance(attendanceList.get(i));
			return new ModelAndView("redirect:/admin/attendance_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/teachers_portal/viewAttendance")
	public ModelAndView viewAttendanceTeachers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("viewTeacherAttendance");
			model.addObject("teacherAttendance",new TeacherAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/teachers_portal/viewAttendance/List", method=RequestMethod.POST)
	public ModelAndView viewAttendanceTeachersList(@ModelAttribute("teacherAttendance") TeacherAttendance obj) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
				ModelAndView model = new ModelAndView("viewTeacherAttendanceList");
				TeacherAttendanceWrapper wrapper = new TeacherAttendanceWrapper();
				ArrayList<TeacherAttendance> teacherAttendanceList = teacherAttendanceService.getAttendance(obj.getDate());
				wrapper.setTeacherAttendance(teacherAttendanceList);
				model.addObject("wrapper",wrapper);
				return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/staff_portal/addAttendance")
	public ModelAndView addAttendanceStaff() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addStaffAttendance");
			model.addObject("staffAttendance",new StaffAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/staff_portal/addAttendance/List", method=RequestMethod.POST)
	public ModelAndView addAttendanceStaffList(@ModelAttribute("staffAttendance") StaffAttendance obj) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("addStaffAttendanceList");
			StaffAttendanceWrapper wrapper = new StaffAttendanceWrapper();
			List<Staff> staffList = staffService.getAllStaffs();
			ArrayList<StaffAttendance> staffAttendanceList = new ArrayList<>();
			for(int i=0;i<staffList.size();i++) {
				staffAttendanceList.add(new StaffAttendance());
				staffAttendanceList.get(i).setDate(obj.getDate());
				staffAttendanceList.get(i).setStaffId(staffList.get(i).getStaffId());
			}
			wrapper.setStaffAttendance(staffAttendanceList);
			model.addObject("wrapper",wrapper);
			ArrayList<String> yesOrNo = new ArrayList<>();
			yesOrNo.add("YES");
			yesOrNo.add("NO");
			yesOrNo.add("N/A");
			model.addObject("yesOrNo",yesOrNo);
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value = "/admin/staff_portal/saveAttendance", method=RequestMethod.POST)
	public ModelAndView saveAttendanceStaff(@ModelAttribute("wrapper") StaffAttendanceWrapper wrapper) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ArrayList<StaffAttendance> attendanceList = wrapper.getStaffAttendance();
			for(int i=0;i<attendanceList.size();i++)
				staffAttendanceService.addStaffAttendance(attendanceList.get(i));
			return new ModelAndView("redirect:/admin/attendance_portal");
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping("/admin/staff_portal/viewAttendance")
	public ModelAndView viewAttendanceStaff() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("viewStaffAttendance");
			model.addObject("staffAttendance",new StaffAttendance());
			return model;
		}
		return new ModelAndView( "redirect:/login");
	}
	
	@Transactional
	@RequestMapping(value="/admin/staff_portal/viewAttendance/List", method=RequestMethod.POST)
	public ModelAndView viewAttendanceStaffList(@ModelAttribute("staffAttendance") StaffAttendance obj) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
				ModelAndView model = new ModelAndView("viewStaffAttendanceList");
				StaffAttendanceWrapper wrapper = new StaffAttendanceWrapper();
				ArrayList<StaffAttendance> staffAttendanceList = staffAttendanceService.getAttendance(obj.getDate());
				wrapper.setStaffAttendance(staffAttendanceList);
				model.addObject("wrapper",wrapper);
				return model;
		}
		return new ModelAndView( "redirect:/login");
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

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
    <c:url var="logoutUrl" value="/logout"/>
    <form class="form-inline" action="${logoutUrl}" method="post" id="logoutForm">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
	<br>
	<h2><a href="/ignite.tutorials/admin/students_portal"><u>Students Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/guardians_portal"><u>Guardians Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/staff_portal"><u>Staff Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/teachers_portal"><u>Teachers Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/courses_portal"><u>Courses Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/enrollments_portal"><u>Enrollments Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/exams_portal"><u>Exams Portal</u></a></h2>
	<h2><a href="/ignite.tutorials/admin/attendance_portal"><u>Attendance Portal</u></a></h2>

</body>
</html>
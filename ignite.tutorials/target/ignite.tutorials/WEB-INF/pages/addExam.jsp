<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<html>
<body>

<h2>${title}</h2>
<h2>${message}</h2>
<c:url var="action" value="/admin/exams_portal/addExam/save" ></c:url>
<form:form method="post" action="${action}" modelAttribute="exam" >
<table>
	<tr>
		<td><form:label path="examId">Exam Id</form:label></td>
		<td><form:input path="examId" required="required"/></td> 
    </tr>
	<tr>
		<td>Course :</td>
		<td>
			<form:select path="courseId" required="required">
		  		<form:options items="${courseList}" itemValue="courseId" itemLabel="representation" />
	       	</form:select>
        </td>
	</tr>
    <tr>
    	<td><form:label path="examName">Exam Name</form:label></td>
       	<td><form:input path="examName" required="required"/></td> 
    </tr>
    <tr>
        <td><form:label path="examDate">Exam Date</form:label></td>
        <td><form:input type="date" path="examDate" required="required"/></td> 
    </tr>
    <tr>
        <td><form:label path="startTiming">Start Timing</form:label></td>
        <td><form:input path="startTiming" required="required"/></td> 
    </tr>
    <tr>
        <td><form:label path="endTiming">End Timing</form:label></td>
        <td><form:input path="endTiming" required="required"/></td> 
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


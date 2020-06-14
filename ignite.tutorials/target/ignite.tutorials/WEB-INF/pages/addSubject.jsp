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
<c:url var="action" value="/admin/courses_portal/addSubject/CourseId=${subject.courseId}/save" ></c:url>
<form:form method="post" action="${action}" modelAttribute="subject" >
<table>
  	<c:if test="${not empty subject.courseId}">
    <tr>
        <td>
            <form:label path="courseId">
                Course Id
            </form:label>
        </td>
        
        <td>
            <form:input path="courseId" readonly="true" disabled="true" />
            <form:hidden path="courseId" />
        </td> 
    </tr>
    </c:if> 
	<tr>
		<td><form:label path="subjectName">Subject Name</form:label></td>
		<td><form:input path="subjectName" required="required"/></td> 
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


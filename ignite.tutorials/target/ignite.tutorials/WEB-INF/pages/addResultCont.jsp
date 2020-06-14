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
<c:url var="action" value="/admin/exams_portal/addResult/3/course=${CourseId}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="result" >
<table>
    <tr>
		<td>Exam :</td>
		<td>
			<form:select path="examId" required="required">
		  		<form:options items="${examList}" itemValue="examId" itemLabel="examId" />
	       	</form:select>
        </td>
	</tr>
	
    <tr>
        <td><form:label path="resultDeclarationDate">Date of Declaration</form:label></td>
        <td><form:input type="date" path="resultDeclarationDate" placeholder="YYYY-MM-DD" required="required"/></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


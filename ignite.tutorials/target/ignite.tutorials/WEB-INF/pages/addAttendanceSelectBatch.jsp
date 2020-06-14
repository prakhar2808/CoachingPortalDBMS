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
<c:url var="action" value="/admin/students_portal/addAttendance/${courseId}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="studentAttendance" >
<table>
	<tr>
		<td>Batch :</td>
		<td>
			<form:select path="batchId" required="required">
		  		<form:options items="${batchList}" itemValue="batchId" itemLabel="representation" />
	       	</form:select>
        </td>
	</tr>
	<tr>
		<td><form:label path="date">Date</form:label></td>
        <td><form:input type="date" path="date" placeholder="YYYY-MM-DD" required="required"/></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


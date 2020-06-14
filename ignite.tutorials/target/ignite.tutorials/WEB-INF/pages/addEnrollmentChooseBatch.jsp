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
<c:url var="action" value="/admin/enrollments_portal/addEnrollment/course=${courseId}/save" ></c:url>
<form:form method="post" action="${action}" modelAttribute="enrollment" >
<table>
	<tr>
		<td><form:label path="transactionNo">Transaction No</form:label></td>
		<td><form:input path="transactionNo" required="required"/></td> 
    </tr>
	<tr>
		<td><form:label path="joinDate">Join Date</form:label></td>
		<td><form:input type="date" path="joinDate" required="required"/></td> 
    </tr>
    <tr>
    	<td><form:label path="endDate">End Date</form:label></td>
       	<td><form:input type="date" path="endDate" required="required"/></td> 
    </tr>
    <tr>
    	<td><form:label path="rollNo">Roll No</form:label></td>
       	<td><form:input path="rollNo" required="required"/></td> 
    </tr>
    <tr>
		<td>Enrolled By :</td>
		<td>
			<form:select path="enrolledBy" required="required">
		  		<form:options items="${staffList}" itemValue="staffId" itemLabel="representation" />
	       	</form:select>
        </td>
	</tr>
	<tr>
		<td>Batch :</td>
		<td>
			<form:select path="batchId" required="required">
		  		<form:options items="${batchList}" itemValue="batchId" itemLabel="representation" />
	       	</form:select>
        </td>
	</tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


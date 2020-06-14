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
<c:url var="action" value="/admin/courses_portal/addBatch/save${course}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="batch" >
<table>
	<tr>
		<td><form:label path="batchId">Batch Id</form:label></td>
		<td><form:input path="batchId" required="required"/></td> 
    </tr>
    <tr>
    	<td><form:label path="roomNo">Room No</form:label></td>
        <td><form:select path="roomNo" required="required">
		  		<form:options items="${roomNos}"/>
	       	</form:select>
       	</td>
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


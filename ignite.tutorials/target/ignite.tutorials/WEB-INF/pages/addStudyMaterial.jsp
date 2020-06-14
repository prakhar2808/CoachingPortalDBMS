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
<c:url var="action" value="/admin/courses_portal/addStudyMaterial/save" ></c:url>
<form:form method="post" action="${action}" modelAttribute="studyMaterial" >
<table>
	<tr>
		<td><form:label path="materialCode">Material Code</form:label></td>
		<td><form:input path="materialCode" required="required"/></td> 
    </tr>
	<c:if test="${not empty studyMaterial.courseId}">
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
		<td><form:label path="topicName">Topic Name</form:label></td>
		<td><form:input path="topicName" required="required"/></td> 
    </tr>
	<tr>
		<td>Level :</td>
		<td>
			<form:select path="level" required="required">
		  		<form:options items="${levelList}"/>
	       	</form:select>
        </td>
	</tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


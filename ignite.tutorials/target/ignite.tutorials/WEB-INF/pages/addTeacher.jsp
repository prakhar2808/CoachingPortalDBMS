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
<c:url var="action" value="/admin/teachers_portal/add/TEA" ></c:url>
<form:form method="post" action="${action}" modelAttribute="teacher" >
<table>
	<tr>
		<td><form:label path="firstName">First Name</form:label></td>
		<td><form:input path="firstName" required="required"/></td> 
    </tr>
    <tr>
    	<td><form:label path="lastName">Last Name</form:label></td>
       	<td><form:input path="lastName" required="required"/></td> 
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" required="required"/></td> 
    </tr>
    <tr>
    	<td><form:label path="gender">Gender (M/F)</form:label></td>
        <td><form:select path="gender" required="required">
		  		<form:options items="${genderList}"/>
	       	</form:select>
       	</td>
    </tr>
    <tr>
        <td><form:label path="dob">DOB</form:label></td>
        <td><form:input type="date" path="dob" placeholder="YYYY-MM-DD" required="required"/></td>
    </tr>
    <tr>
        <td><form:label path="houseNo">House No</form:label></td>
        <td><form:input path="houseNo" required="required" /></td>
    </tr>    
    <tr>
        <td><form:label path="street">Street</form:label></td>
        <td><form:input path="street" required="required" /></td>
    </tr>
    <tr>
        <td><form:label path="city">City</form:label></td>
        <td><form:input path="city" required="required" /> </td>
    </tr>
    <tr>
        <td><form:label path="accountNo">AccountNo</form:label></td>
        <td><form:input path="accountNo" required="required" /> </td>
    </tr>
    <tr>
        <td><form:label path="salary">Salary (Per Month)</form:label></td>
        <td><form:input path="salary" required="required" /> </td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Submit"/></td>
    </tr>

</table>    
</form:form>


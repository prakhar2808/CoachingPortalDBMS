<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
	body {
		background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
		color: #444;
		font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
		text-shadow: 0 1px 0 #fff;
	}
	
	strong {
		font-weight: bold; 
	}
	
	em {
		font-style: italic; 
	}
	
	table {
		background: #f5f5f5;
		border-collapse: separate;
		box-shadow: inset 0 1px 0 #fff;
		font-size: 15px;
		line-height: 15px;
		margin: 10px auto;
		text-align: left;
		width: 1000px;
	}	
	
	th {
		background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
		border-left: 1px solid #555;
		border-right: 1px solid #777;
		border-top: 1px solid #555;
		border-bottom: 1px solid #333;
		box-shadow: inset 0 1px 0 #999;
		color: #fff;
	  font-weight: bold;
		padding: 10px 5px;
		position: relative;
		text-shadow: 0 1px 0 #000;	
	}
	
	th:after {
		background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
		content: '';
		display: block;
		height: 25%;
		left: 0;
		margin: 1px 0 0 0;
		position: absolute;
		top: 25%;
		width: auto;
	}
	
	th:first-child {
		border-left: 1px solid #777;	
		box-shadow: inset 1px 1px 0 #999;
	}
	
	th:last-child {
		box-shadow: inset -1px 1px 0 #999;
	}
	
	td {
		border-right: 1px solid #fff;
		border-left: 1px solid #e8e8e8;
		border-top: 1px solid #fff;
		border-bottom: 1px solid #e8e8e8;
		padding: 10px 15px;
		position: relative;
		transition: all 300ms;
	}
	
	td:first-child {
		box-shadow: inset 1px 0 0 #fff;
	}	
	
	td:last-child {
		border-right: 1px solid #e8e8e8;
		box-shadow: inset -1px 0 0 #fff;
	}	
	
	tr {
		background: url(https://jackrugile.com/images/misc/noise-diagonal.png);	
	}
	
	tr:nth-child(odd) td {
		background: #f1f1f1 url(https://jackrugile.com/images/misc/noise-diagonal.png);	
	}
	
	tr:last-of-type td {
		box-shadow: inset 0 -1px 0 #fff; 
	}
	
	tr:last-of-type td:first-child {
		box-shadow: inset 1px -1px 0 #fff;
	}	
	
	tr:last-of-type td:last-child {
		box-shadow: inset -1px -1px 0 #fff;
	}	
	
	tbody:hover td {
		color: #777;
	}
	
	tbody:hover tr:hover td {
		color: #444;
		text-shadow: 0 1px 0 #fff;
	}
</style>
</head>
<body>
	<h1>Welcome to Teacher Home</h1>
	<h2>You are logged in as TEA${teacher.teacherId}</h2>

	<c:url var="logoutUrl" value="/logout"/>
    <form class="form-inline" action="${logoutUrl}" method="post" id="logoutForm">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    
    <table>
    <tr>
    	<th>Teacher Id</th>
    	<td>${teacher.teacherId}</td>
   	</tr>    
   	<tr>
    	<th>Name</th>
    	<td>${teacher.firstName} ${teacher.lastName}</td>
   	</tr>
    </table>
    
    <h3>List of Batches teaching:</h3>
    <table>
    <tr>
    <th>Course Id</th>
    <th>Batch Id</th>
    <th>Students</th>
    <c:forEach items="${teachesList}" var="teaches">
        <tr>
        	<td>${teaches.courseId}</td>
        	<td>${teaches.batchId }</td>
        	<td><a href="/ignite.tutorials/teacher/viewStudents/${teaches.courseId}/${teaches.batchId}">View</a></td>
        </tr>
    </c:forEach>
    </table>
        <table>
    	<tr>
    		<th>Your attendance</th>
    		<td><a href="/ignite.tutorials/teacher/attendance">View</a></td>
    	</tr>
    </table>
    </body>
</html>
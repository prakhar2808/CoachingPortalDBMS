<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
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
<script type="text/javascript">
    function deleteSM(courseId,smCode){
        if(confirm('Do you want to delete this Study Material ?')){
            var url = 'delete/COU'+courseId+'/SM'+smCode;
            window.location.href = url;
        }
    }
</script>
</head>
<body>
<h1>${title}</h1>
<h1>${message}</h1>
<h1>List of your study materials :</h1>
<c:if test="${not empty smList}">
    <table class="userTable">
    <tr>
    	<th width="90">Code</th>
    	<th width="90">Course Id</th>
        <th width="90">Topic Name</th>  
        <th width="90">Level</th>
        <th width="90"></th>  
        </tr>
    
    <c:forEach items="${smList}" var="sm">
        <tr>
        	<td>${sm.materialCode}</td>
        	<td>${sm.courseId }</td>
        	<td>${sm.topicName }</td>
        	<td>${sm.level }</td>
            <td><a href="#" onclick="javascript:deleteSM('${sm.courseId}','${sm.materialCode}')"> Delete </a>
        </tr>
    </c:forEach>
    </table>
</c:if>






</body>
</html>
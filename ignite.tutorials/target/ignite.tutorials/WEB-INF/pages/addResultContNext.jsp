<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<html>
<head>
<style type="text/css">
        body {
            font-family: Arial;
            font-size: 10px;
            margin: 30px;
        }
        .userTable, .userTable td {
            border-collapse: collapse;
            border: 1px solid #0000FF;
            margin: 2px;
            padding: 2px 2px 2px 10px;
            font-size: 14px;
        }
        .userTable th {
            font-weight: bold;
            font-size: 14px;
            background-color: #5C82FF;
            color: white;
        }
        .userLabel {
            font-family: Arial;
            font-size: 14px;
            font-weight: bold;
        }
        a, a:AFTER {
            color: blue;
        }
    </style>
</head>
<body>

<h2>${title}</h2>
<h2>${message}</h2>
<c:url var="action" value="/admin/exams_portal/addResult/save" ></c:url>
<form:form method="post" action="${action}" modelAttribute="wrapper" >

 <table class="userTable">
                <tr>
                    <th width="100">Roll No</th>
                    <th width="100">Course Id</th>
                    <th width="100">Exam Id</th>
                    <th width="100">Result Declaration Date</th>
                    <th width="100">Marks</th>
                    <th width="100">Rank</th>
                </tr>
            
				<c:forEach varStatus="i" var="resultObj" items="${wrapper.result}" >
					<tr>
						<td><form:input type="hidden" path="result[${i.index}].rollNo"/>${resultObj.rollNo}</td>
					
						<td><form:input type="hidden" path="result[${i.index}].courseId"/>${resultObj.courseId}</td>
					
						<td><form:input type="hidden" path="result[${i.index}].examId"/>${resultObj.examId}</td>
					
						<td><form:input type="hidden" path="result[${i.index}].resultDeclarationDate"/>${resultObj.resultDeclarationDate}</td>
						
						<td><form:input path="result[${i.index}].marks"/></td>
						<td><form:input path="result[${i.index}].studentRank"/></td>
					</tr>
			    </c:forEach>
	    </table>
	    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>


<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 
<h2>Edit Student</h2>
<c:url var="action" value="save_changes/BAT${batchId}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="batch">
    <table>
   
  <c:if test="${not empty batch.batchId}">
    <tr>
        <td>
            <form:label path="batchId">
                Batch Id
            </form:label>
        </td>
        
        <td>
            <form:input path="batchId" readonly="true" disabled="true" />
            <form:hidden path="batchId" />
        </td> 
    </tr>
    </c:if> 
    <c:if test="${not empty batch.courseId}">
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
    	<td><form:label path="roomNo">Room No</form:label></td>
        <td><form:select path="roomNo" required="required">
		  		<form:options items="${roomNos}"/>
	       	</form:select>
       	</td>
    </tr>
    <tr>
        <td>
            <form:label path="startTiming">
                Start Timing
            </form:label>
        </td>
       
        <td>
            <form:input path="startTiming" required="required"/>
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="endTiming">
                End Timing
            </form:label>
        </td>
       
        <td>
            <form:input path="endTiming" required="required"/>
        </td> 
    </tr>
   
    <tr>
        <td colspan="2">
                <input type="submit"
                    value="Save" />
        </td>
    </tr>

</table>    
</form:form>
  
</body>
</html>
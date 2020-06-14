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
 
<h2>Edit Result</h2>
<c:url var="action" value="save_changes/${result.examId}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="result">
    <table>
   
  <c:if test="${not empty result.rollNo}">
    <tr>
        <td>
            <form:label path="rollNo">
                Roll No
            </form:label>
        </td>
        
        <td>
            <form:input path="rollNo" readonly="true" disabled="true" />
            <form:hidden path="rollNo" />
        </td> 
    </tr>
    </c:if> 
    <c:if test="${not empty result.courseId}">
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
   <c:if test="${not empty result.examId}">
    <tr>
        <td>
            <form:label path="examId">
                Exam Id
            </form:label>
        </td>
        
        <td>
            <form:input path="examId" readonly="true" disabled="true" />
            <form:hidden path="examId" />
        </td> 
    </tr>
   </c:if> 
      <c:if test="${not empty result.resultDeclarationDate}">
    <tr>
        <td>
            <form:label path="resultDeclarationDate">
                Result Declaration Date
            </form:label>
        </td>
        
        <td>
            <form:input type="date" path="resultDeclarationDate" readonly="true" disabled="true" />
            <form:hidden path="resultDeclarationDate" />
        </td> 
    </tr>
   </c:if> 
   
    <tr>
        <td>
            <form:label path="marks">
                Marks
            </form:label>
        </td>
       
        <td>
            <form:input path="marks" required="required"/>
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="studentRank">
                Rank
            </form:label>
        </td>
       
        <td>
            <form:input path="studentRank" required="required"/>
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
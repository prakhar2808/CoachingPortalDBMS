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
<c:url var="action" value="save_changes/TEA${TeacherId}" ></c:url>
<form:form method="post" action="${action}" modelAttribute="teacher">
    <table>
   
  <c:if test="${not empty user.teacherId}">
    <tr>
        <td>
            <form:label path="teacherId">
                Roll No
            </form:label>
        </td>
        
        <td>
            <form:input path="teacherId" readonly="true" size="10"  disabled="true" />
            <form:hidden path="teacherId" />
        </td> 
    </tr>
   </c:if> 
   
    <tr>
        <td>
            
            <form:label path="firstName">
                First Name
            </form:label>
        </td>
       
        <td>
            <form:input path="firstName" required="required"/>
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="lastName">
                Last Name
            </form:label>
        </td>
       
        <td>
            <form:input path="lastName" required="required"/>
        </td> 
    </tr>
        <tr>
        <td>
            <form:label path="password">
                Password
            </form:label>
        </td>
       
        <td>
            <form:input path="password" required="required"/>
        </td> 
    </tr>
    <tr>
        
        <td>
            <form:label path="gender">
                Gender (M/F)
            </form:label>
        </td>
        
        <td>
            <form:input path="gender" required="required"/>
        </td>
    </tr>
    
    <tr>
        
        <td>
            <form:label path="dob">
                DOB
            </form:label>
        </td>
        
        <td>
            <form:input type="date" path="dob" placeholder="YYYY-MM-DD" required="required"/>
        </td>
    </tr>
   
    <tr>
        
        <td>
            <form:label path="houseNo">
                House No
            </form:label>
        </td>
       
        <td>
            <form:input path="houseNo" required="required" />
        </td>
    </tr>    
    <tr>
        
        <td>
            <form:label path="street">
                Street
            </form:label>
        </td>
       
        <td>
            <form:input path="street" required="required" />
        </td>
    </tr>
        <tr>
        
        <td>
            <form:label path="city">
                City
            </form:label>
        </td>
       
        <td>
            <form:input path="city" required="required" />
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
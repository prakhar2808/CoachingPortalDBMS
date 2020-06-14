<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url var="logoutUrl" value="/logout"/>
    <form class="form-inline" action="${logoutUrl}" method="post" id="logoutForm">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form></body>
</html>
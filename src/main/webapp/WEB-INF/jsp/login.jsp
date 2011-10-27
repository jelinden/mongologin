<%@ page session="false" contentType="text/html; charset=utf-8" 
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head><title>login</title></head>
  <body>
<h1>Login</h1>
<div style="border: 1px solid gray;">${error}</div>
<form:form action="" method="post" modelAttribute="user">
  <table>
    <tr>
      <td>Username: </td><td><form:input path="userName"/></td>
    </tr>
    <tr>
      <td>Password: </td><td><form:password path="password"/></td>
    </tr>  
  </table>
  <input type="submit" value="Login"/>
</form:form>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<table class="table">
   <thead>
   <tr>
      <th>ID</th>
      <th>name</th>
      <th>birthday</th>
      <th>loginID</th>
      <th>city</th>
      <th>email</th>
      <th>description</th>
   </tr>
   </thead>
   <tbody>
   <c:forEach var="user" items="${users}">
      <tr>
         <td scope="row">${user.id}</td>
         <td>${user.name}</td>
         <td>${user.birthday}</td>
         <td>${user.loginID}</td>
         <td>${user.city}</td>
         <td>${user.email}</td>
         <td>${user.description}</td>
         <td><a href="${pageContext.request.contextPath}/showuser?id=${user.id}">Link</a></td>
      </tr>
   </c:forEach>
   </tbody>
</table>

<br>
<a href="/users/">Main page</a>
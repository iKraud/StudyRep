<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<ul class="list-group">
   <li class="list-group-item">${user.id}</li>
   <li class="list-group-item">${user.name}</li>
   <li class="list-group-item">${user.birthday}</li>
   <li class="list-group-item">${user.loginID}</li>
   <li class="list-group-item">${user.city}</li>
   <li class="list-group-item">${user.email}</li>
   <li class="list-group-item">${user.description}</li>
</ul>

<br>
<a href="/users/">Main page</a>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<p>${greeting }</p>
	<p class="h4">Welcome to users page.</p>	
	<c:forEach var="usr" items="${userList}">
		<p>${usr.name}</p>
	</c:forEach>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div>
<p class="text-center lvg-error-message">APPLICATION ERROR!</p>
<p class="text-center lvg-error-message">${errorMessage }</p>
<p class="text-center lvg-error-message">${exception }</p>	
</div>
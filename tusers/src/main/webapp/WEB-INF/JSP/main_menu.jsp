<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class="navbar navbar-default">
	<div id="lvg-main-menu" class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
				data-toggle="collapse" 
				data-target="#bs-example-navbar-collapse-1" 
				aria-expanded="false">
					 <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<spring:url value="/"/>'>TUsers</a>
		</div>
		 <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">     
      
      <c:if test="${currentUser!=null }">
      <p class="navbar-text navbar-left">
      	${currentUser.name } ${currentUser.surname } |
      	${currentUser.birthDate } |
      	${currentUser.email }</p>          
      
      <ul class="nav navbar-nav navbar-right">        
        <li><a href="logout">Logout <i class="fa fa-sign-out"></i></a></li>                    
      </ul>  
      <p class="navbar-text navbar-right">Sign in as 
      	<a href='#' class="navbar-link">${currentUser.name } ${currentUser.surname }</a></p>     
      </c:if>    
    </div><!-- /.navbar-collapse -->
	</div>
</nav>

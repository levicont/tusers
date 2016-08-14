<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
      <ul class="nav navbar-nav">
        <li><a href="#">${currentUser.name }</a></li>
        <li><a href="#">${currentUser.surname }</a></li>
        <li><a href="#">${currentUser.birthday }</a></li>
        <li><a href="#">${currentUser.email }</a></li>
                    
      </ul>      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Preferences</a></li>        
      </ul>
    </div><!-- /.navbar-collapse -->
	</div>
</nav>

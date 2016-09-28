<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url value="/signin" var="springSecurityURL"/>
<div class="row">
	<div class="col-md-offset-3 col-lg-6">
		<form:form modelAttribute="user" method="post">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="h4">Sign in</p>
				</div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-offset-3 col-lg-6">
							<c:if test="${error != null}">
								<p class="lvg-error text-center">Error:${error }</p>
							</c:if>
							<c:if test="${registrationOK != null }">
								<p class="lvg-valid text-center"><i class="fa fa-chevron-circle-down"></i> Registration success! You can login!</p>
								
							</c:if>
							<p class="lvg-error text-center">${errorMessage }</p>
							<div class="form-group">
								<label for="email">E-mail</label>
								<form:input path="email" placeHolder="E-mail" id="email" cssClass="form-control" />									
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-offset-3 col-lg-6">
							<div class="form-group">
								<label for="password">Password</label>
								<form:password path="password" placeHolder="Password" id="password"
									cssClass="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-3 col-lg-6">
							<div class="form-group">
								<input type="checkbox" name="remember-me" title="Remember"/>
								<label>Remember</label>
							</div>
						</div>
					</div>
				</div>

				<div class="panel-footer">
				<div class="row">
						<div class="col-md-offset-3 col-lg-3">
							<input class="btn btn-primary" type="submit" value="Sign in">														
						</div>
						<div class="col-lg-3 col-md-offset-3">
							<a class="btn btn-primary" href="registration">Registration</a>														
						</div>
						
					</div>					
				</div>
			</div>

		</form:form>
	</div>

</div>
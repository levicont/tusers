<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
	<div class="col-md-offset-3 col-lg-6">
		<form:form modelAttribute="user" method="POST" action="javascript:sbmUser();" id="registerForm">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="h4">Registration</p>
				</div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="name">Name</label>
								<form:input path="name" placeHolder="Name" id="name"
									cssClass="form-control" />
								<form:errors path="name" cssClass="lvg-error"></form:errors>
								<p id="name-lvg-error" class="lvg-error"></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="surname">Surname</label>
								<form:input path="surname" placeHolder="Surname" id="surname"
									cssClass="form-control" />
								<form:errors path="surname" cssClass="lvg-error"></form:errors>
								<p id="surname-lvg-error" class="lvg-error"></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="birthday">Birthday</label>
								<div class='input-group date' id='datetimepicker'>
									<form:input path="birthday" id="birthday"
										cssClass="form-control" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<script type="text/javascript">
							$(function() {
								$('#datetimepicker').datetimepicker({
									locale : 'ru',
									format : 'DD.MM.YYYY'
								});
							});
						</script>
					</div>
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="email">E-mail</label>
								<form:input path="email" placeHolder="E-mail" id="email"
									cssClass="form-control" />
								<form:errors path="email" cssClass="lvg-error"></form:errors>
								<p id="email-lvg-error" class="lvg-error"></p>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="password">Password</label>
								<form:password path="password" placeHolder="Password"
									id="password" cssClass="form-control" />
								<form:errors path="password" cssClass="lvg-error"></form:errors>
								<p id="password-lvg-error" class="lvg-error"></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="password">Re enter password</label>
								<form:password path="password"
									placeHolder="Enter password again" id="repassword"
									cssClass="form-control" />
								<form:errors path="password" cssClass="lvg-error"></form:errors>
								<p id="repassword-lvg-error" class="lvg-error"></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-2 col-lg-7">
							<div class="form-group">
								<label for="info">More information</label>
								<form:textarea path="info" id="info" cssClass="form-control" />
							</div>
						</div>
					</div>
				</div>

				<div class="panel-footer">
					<div class="row">
						<div class="col-md-offset-2 col-lg-3">
							<input class="btn btn-primary" type="submit" 
								value="Register">
						</div>
					</div>
				</div>
			</div>

		</form:form>
	</div>
</div>


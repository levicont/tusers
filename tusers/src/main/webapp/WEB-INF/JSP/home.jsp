<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-12 text-center">
					<c:if test="${currentUser.galleries != null }">
						<p class="h4">Gallery</p>
						<!-- Must fix Lazy Init Exception -->
						<c:forEach var="gallery" items="${currentUser.galleries}">
							<p>${gallery.name}</p>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<img alt="Main Image" width="400" style="border: 1px solid #50C878"
						src="${mainImage }">
				</div>
			</div>

		</div>
		<div class="col-lg-6">
			<!-- FORM Container -->
			<form:form modelAttribute="uploadFileForm" action="upload" enctype="multipart/form-data"
				method="post">
				<input id="_csrf" type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<p class="h4">Upload images</p>
					</div>

					<div id="file-list-container" class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<p id="upload-error" class="lvg-error"></p>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-3 text-right">
								<p>Selected files:</p>
							</div>
							<div class="col-lg-2 text-left">
								<p id="file-count">0</p>
							</div>
							<div class="col-lg-2 text-right">
								<p>Total size:</p>
							</div>
							<div class="col-lg-2 text-left">
								<p id="file-size">0</p>
							</div>
							<div class="col-lg-3 text-right">
								<div class="form-group">
									<label class="btn btn-default btn-file"> Browse <form:input
										id="file-field" path="file" type="file" cssStyle="display: none;"/>
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-lg-offset-9 col-lg-3 text-right">
								<input id="upload-all" class="btn btn-primary" type="button"
									value="Upload">
							</div>
						</div>
					</div>
				</div>
			</form:form>
			<!-- END FORM Container -->
		</div>
	</div>


</div>
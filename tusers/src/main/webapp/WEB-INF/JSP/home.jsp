<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

		</div>
		<div class="col-lg-6">
			<!-- FORM Container -->
			<form action="upload" enctype="multipart/form-data" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<p class="h4">Upload images</p>
					</div>

					<div id="file-list-container" class="panel-body">
						<div class="row">
							<div class="col-lg-3">
								<p>Selected files:</p>
							</div>
							<div class="col-lg-2 text-left">
								<p id="file-count">0</p>
							</div>
							<div class="col-lg-3">
								<p>Total size:</p>
							</div>
							<div class="col-lg-1 text-left">
								<p id="file-size">0</p>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="btn btn-default btn-file"> Browse <input
										id="file-field" type="file" multiple
										accept="image/jpeg, image/jpg, image/png"
										style="display: none;">
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-2">
								<img class="lvg-img-icon" alt="Image" src="">
							</div>
							<div class="col-lg-6">
								<p class="lvg-img-name text-left">Image name</p>
							</div>
							<div class="col-lg-4">
								<div class="progress">
									<div 	class="progress-bar progress-bar-success" 
  											role="progressbar" 
  											aria-valuenow="1"
  										 	aria-valuemin="0" 
  										 	aria-valuemax="100" 
  										 	style="width:10%"></div>  									
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-lg-offset-9 col-lg-3">
								<input id="upload-all" class="btn btn-primary" type="button" value="Upload">
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- END FORM Container -->
		</div>
	</div>


</div>
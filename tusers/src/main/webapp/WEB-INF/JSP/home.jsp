<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-12 text-center">
					<p class="h4">Gallery</p>
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

					<div class="panel-body">
						<div class="row">
							<div class="col-md-offset-2 col-lg-7">
								<div class="form-group">
									<label class="btn btn-default btn-file"> Browse 
										<input 	type="file" multiple 
												accept="image/jpeg, image/jpg, image/png"
												style="display: none;">
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-md-offset-2 col-lg-3">
								<input class="btn btn-primary" type="submit" value="Upload">
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- END FORM Container -->
		</div>
	</div>


</div>
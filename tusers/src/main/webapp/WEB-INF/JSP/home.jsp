<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<spring:url value="/img" var="imageUrl" />
<spring:url value="/files/img/load-icon.gif" var="loadImageUrl" />
<script type="text/javascript">
	$(function() {
		/* initiate lazyload defining a custom event to trigger image loading  */
		$("ul li img").lazyload({
			event : "turnPage",
			effect : "fadeIn"
		});
		/* initiate plugin */
		$("div.holder").jPages({
			containerID : "itemContainer",
			perPage : 5,
			animation : "fadeInUp",
			callback : function(pages, items) {
				/* lazy load current images */
				items.showing.find("img").trigger("turnPage");
				/* lazy load next page images */
				items.oncoming.find("img").trigger("turnPage");
			}
		});

		$(".lvg-img-item").click(function(e) {
			currentImage = $(this);
			mainImage = $("#main-image");
			if (mainImage) {
				mainImage.attr("src", currentImage.attr("src"));
			}
		});
		$(".lvg-img-item").hover(function(e) {
			currentImage = $(this);
			currentImage.addClass("lvg-active-image-item");
		}, function(e) {
			currentImage = $(this);
			currentImage.removeClass("lvg-active-image-item");
		});
	});
</script>

<div class="container">
	<div class="row">
		<div class="col-lg-7 text-center">
			<c:if test="${currentUser.galleries != null }">
				<p class="h4">Gallery</p>
				<c:forEach var="gallery" items="${currentUser.galleries}" end="1">
					<c:set var="currentGallery" value="${gallery}" />
					<p>${gallery.name}</p>
					<!-- END GALLERY ITEMS -->
				</c:forEach>
				<c:if test="${not empty currentGallery}">
					<c:forEach items="${currentGallery.images }" var="img" end="0">
						<c:set var="mainImg" value="${img}" />
						<div id="mainImageContainer" class="row">
							
								<table id="main-image-holder" class="col-lg-12 text-center">
									<tbody>
										<tr>
											<c:if test="${not empty mainImg }">
												<td class="text-center align-middle"><img id="main-image"
													alt="Main Image" src='${imageUrl}/${mainImg.id }'></td>
											</c:if>
										</tr>
									</tbody>
								</table>
							
						</div>
						<!-- END IMAGE ITEMS -->
					</c:forEach>
					<div id="pagingImageItemsContainer" class="row">
						<div class="col-lg-12 text-center">
							<div class="holder"></div>
							<ul id="itemContainer" class="list-unstyled list-inline">
								<c:forEach items="${currentGallery.images }" var="imageItem">
									<li class="lvg-jp-item"><img src="${loadImageUrl}"
										data-original="${imageUrl}/${imageItem.id}"
										class="image-thumbnail lvg-img-item" height="65" width="80">
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
			</c:if>
		</div>
		<div class="col-lg-5 text-right">
			<!-- FORM Container -->
			<form action="upload" enctype="multipart/form-data" method="post">
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
									<label class="btn btn-default btn-file"> Browse <input
										id="file-field" type="file" style="display: none;" multiple />
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
			</form>
			<!-- END FORM Container -->
		</div>
	</div>


</div>
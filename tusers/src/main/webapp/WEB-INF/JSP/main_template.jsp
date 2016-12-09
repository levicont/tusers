<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>TUsers</title>

<!-- JS Scripts links -->
<spring:url value="/files/js/jquery-2.2.2.min.js" var="jqueryJS" />
<spring:url value="/files/js/moment/min/moment-with-locales.min.js"
	var="momentWithLocalesJS" />
<spring:url value="/files/js/bootstrap-3.3.6-dist/js/bootstrap.min.js"
	var="bootstrapStrapJS" />
<spring:url
	value="/files/css/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
	var="bootStrapDateTimePickerJS" />
<spring:url value="/files/js/validator.js" var="validatorJS"/>

<spring:url value="/files/js/fileUploaderInterface.js" var="fileUploaderInterface"/>
<spring:url value="/files/js/uploaderObject.js" var="uploaderObject"/>

<!-- jPages -->
<spring:url value="/files/js/jpages/jPages.min.js" var="jPagesUrl"/>
<spring:url value="/files/js/jpages/jquery.lazyload.js" var="jPagesLazyLoadUrl"/>
<spring:url value="/files/js/jpages/css/jPages.css" var="jPagesCssUrl"/>
<spring:url value="/files/js/jpages/css/animate.css" var="jPagesAnimateCssUrl"/>


<!-- CSS links -->
<spring:url value="/files/css/main.css" var="mainCSS" />
<spring:url
	value="/files/css/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	var="bootStrapCSS" />
<spring:url
	value="/files/css/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	var="bootStrapDateTimePickerCSS" />
<spring:url
	value="/files/css/font-awesome-4.5.0/css/font-awesome.min.css"
	var="awesomeCSS" />


<script src="${jqueryJS }"></script>
<script src="${momentWithLocalesJS }"></script>
<script src="${bootstrapStrapJS }"></script>
<script src="${bootStrapDateTimePickerJS }"></script>
<script src="${validatorJS}"> </script> 
<script src="${fileUploaderInterface }"></script>
<script src="${uploaderObject}"> </script>
<script src="${jPagesUrl}"> </script>
<script src="${jPagesLazyLoadUrl}"> </script>
   

<link rel="stylesheet" href="${jPagesCssUrl }" type="text/css">
<link rel="stylesheet" href="${jPagesAnimateCssUrl }" type="text/css">
<link rel="stylesheet" href="${bootStrapCSS }" type="text/css">
<link rel="stylesheet" href="${bootStrapDateTimePickerCSS }" type="text/css">
<link rel="stylesheet" href="${awesomeCSS }">
<link rel="stylesheet" href="${mainCSS }" type="text/css">
	

</head>
<body>
	<div id="content">
		<div id="main">
			<div id="header" class="page-header">
				<tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="menu" />				
			</div>
			<div id="all">
				<div class="container">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
			<!-- END ALL -->

		</div>
		<!-- END MAIN -->
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	<!-- End content -->
</body>
</html>
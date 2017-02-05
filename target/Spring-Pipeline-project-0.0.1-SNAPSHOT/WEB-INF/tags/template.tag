<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" var="fontAwesomeCss" />
<spring:url value="/resources/css/sticky-footer-navbar.css" var="stickyFooterNavbarCss" />
<spring:url value="/resources/css/styles_scrollup.css" var="scrollUpCss" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<spring:url value="https://code.jquery.com/jquery-3.1.1.min.js" var="jQuery" />
<spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery.scrollUp.min.js" var="scrollUpJs" />
<spring:url value="/resources/js/main.js" var="mainJs" />

<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="Windows-1251">
		<title>Pipeline-project</title>
		
		<link href="${bootstrapCss}" rel="stylesheet" />
		<link href="${fontAwesomeCss}" rel="stylesheet" />
		<link href="${stickyFooterNavbarCss}" rel="stylesheet" />
		<link href="${scrollUpCss}" rel="stylesheet" />
		<link href="${mainCss}" rel="stylesheet" />
		
		<script type="text/javascript" src="${jQuery}"
			integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  			crossorigin="anonymous"
  		></script>
		<script type="text/javascript" src="${bootstrapJs}"></script>
		<script type="text/javascript" src="${scrollUpJs}"></script>
		<script type="text/javascript" src="${mainJs}"></script>
		
		<link href="/resources/images/ico/favicon.ico" rel="shortcut icon">
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				
				<div class="navbar-header">
	      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href='<spring:url value="/" />'>Pipeline-project</a>
				</div>
				
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							<a href="<spring:url value="/pipeline" />">
								pipeline
							</a>
						</li>
						<li>
							<a href="<spring:url value="/status" />">
								result status
							</a>
						</li>
						<li>
							<a href="<spring:url value="/execute" />">
								execute
							</a>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
				
			</div><!-- /.container-fluid -->
		</nav>
		
		<div class="container">
			<div class="row">
				<jsp:doBody />
			</div>
		</div>
		
		<footer class="footer">
			<div class="container">
				<div class="row">
					<p class="text-muted">Copyright © 2017 Website created by: Dmitry Belkevich, e-mail: harleydav@mail.ru</p>
				</div>
			</div>
		</footer>
		
	</body>
</html>
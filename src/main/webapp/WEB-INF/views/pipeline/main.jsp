<%@ page
	language="java"
	contentType="text/html; charset=Windows-1251"
	pageEncoding="Windows-1251"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:template>

<h2>
	<i class="fa fa-hdd-o" aria-hidden="true"></i>
	 File Pipelines:
</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>name</th>
			<th>description</th>
			<th>path</th>
			<th>- start -</th>
			<th>- add to database -</th>
			<th>- delete file -</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${filePipelines}" var="pipeline" varStatus="count">
			<tr>
				<td>${count.index + 1}</td>
				<td>${pipeline.name}</td>
				<td>${pipeline.description}</td>
				<td>${pathsPipelines[count.index]}</td>
				<td>
					<a href='<spring:url value="/pipeline/file_start/${pathsPipelines[count.index]}" />'>
						<i class="fa fa-play" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/pipeline/file_add/${pathsPipelines[count.index]}" />'>
						<i class="fa fa-hdd-o" aria-hidden="true"></i>
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
						<i class="fa fa-database" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/pipeline/file_delete/${pathsPipelines[count.index]}" />'>
						<i class="fa fa-trash-o" aria-hidden="true"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>
	<i class="fa fa-database" aria-hidden="true"></i>
	 Database Pipelines:
</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>id</th>
			<th>name</th>
			<th>description</th>
			<th>- start -</th>
			<th>- get -</th>
			<th>- delete -</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${databasePipelines}" var="pipeline" varStatus="count">
			<tr>
				<td>${count.index + 1}</td>
				<td>${pipeline.id}</td>
				<td>${pipeline.name}</td>
				<td>${pipeline.description}</td>
				<td>
					<a href='<spring:url value="/pipeline/database_start/${pipeline.id}" />'>
						<i class="fa fa-play" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/pipeline/database_edit/${pipeline.id}" />'>
						<i class="fa fa-eye" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/pipeline/database_delete/${pipeline.id}" />'>
						<i class="fa fa-trash-o" aria-hidden="true"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<form:form role="form" action='pipeline/new' method="POST" commandName="context">
	<h2>New pipeline</h2>
	
	<div class="form-group">
		<label for="comment">input new pipeline in yaml-format</label>
		<textarea name="context" class="form-control" rows="5" id="comment"></textarea>
	</div>
	
	<button type="submit" class="btn btn-default">Send request</button>
</form:form>

</t:template>
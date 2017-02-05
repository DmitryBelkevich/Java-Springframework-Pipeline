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

<h2>Pipeline:</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>description</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${pipeline.id}</td>
			<td>${pipeline.name}</td>
			<td>${pipeline.description}</td>
		</tr>
	</tbody>
</table>

<h2>Tasks:</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>id</th>
			<th>name</th>
			<th>description</th>
			<th>action (type)</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pipeline.tasks}" var="task" varStatus="count">
			<tr>
				<td>${count.index + 1}</td>
				<td>${task.id}</td>
				<td>${task.name}</td>
				<td>${task.description}</td>
				<td>${task.action.type}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</t:template>
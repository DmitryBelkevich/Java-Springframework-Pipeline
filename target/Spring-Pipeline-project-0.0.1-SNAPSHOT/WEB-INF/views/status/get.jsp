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

<h2>Database Pipeline execute status:</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>id</th>
			<th>pipeline</th>
			<th>status</th>
			<th>start_time</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${pipelineExecutionStatus.executionId}</td>
			<td>${pipelineExecutionStatus.pipeline}</td>
			<td>${pipelineExecutionStatus.status}</td>
			<td>${pipelineExecutionStatus.startTime}</td>
		</tr>
	</tbody>
</table>

<h2>Task execute statuses:</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>status</th>
			<th>start_time</th>
			<th>end_time</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pipelineExecutionStatus.tasks}" var="task" varStatus="count">
			<tr>
				<td>${task.id}</td>
				<td>${task.name}</td>
				<td>${task.status}</td>
				<td>${task.startTime}</td>
				<td>${task.endTime}</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

</t:template>
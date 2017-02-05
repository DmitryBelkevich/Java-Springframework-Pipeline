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
	 File Pipeline execute statuses:
</h2>

<a href='<spring:url value="/status/add" />'>
	<i class="fa fa-hdd-o" aria-hidden="true"></i>
	<i class="fa fa-arrow-right" aria-hidden="true"></i>
	<i class="fa fa-database" aria-hidden="true"></i>
	 add to Database
</a>

<h2>
	<i class="fa fa-database" aria-hidden="true"></i>
	 Database Pipeline execute statuses:
</h2>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>id</th>
			<th>pipeline</th>
			<th>status</th>
			<th>start_time</th>
			<th>- show -</th>
			<th>- to File -</th>
			<th>- delete -</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pipelineExecutionStatuses}" var="pipelineExecutionStatus" varStatus="count">
			<tr>
				<td>${count.index + 1}</td>
				<td>${pipelineExecutionStatus.executionId}</td>
				<td>${pipelineExecutionStatus.pipeline}</td>
				<td>${pipelineExecutionStatus.status}</td>
				<td>${pipelineExecutionStatus.startTime}</td>
				<td>
					<a href='<spring:url value="/status/get/${pipelineExecutionStatus.executionId}" />'>
						<i class="fa fa-eye" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/status/toFile/${pipelineExecutionStatus.executionId}" />'>
						<i class="fa fa-hdd-o" aria-hidden="true"></i>
						<i class="fa fa-arrow-left" aria-hidden="true"></i>
						<i class="fa fa-database" aria-hidden="true"></i>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/status/delete/${pipelineExecutionStatus.executionId}" />'>
						<i class="fa fa-trash-o" aria-hidden="true"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</t:template>
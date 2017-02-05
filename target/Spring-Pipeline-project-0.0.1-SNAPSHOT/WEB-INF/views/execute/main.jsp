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

<div class="panel panel-default">
	<div class="panel-body">
		<form role="form" action="" method="GET">
			
			<h2>execute pipeline</h2>
			
			<div class="form-group">
				<label for="inputPipelineId"></label>
				<input type="text" class="form-control" id="inputPipelineId" placeholder="pipeline id">
			</div>
			
			<div class="form-group">
				<label for="inputPipelineName"></label>
				<input type="text" class="form-control" id="inputPipelineName" placeholder="pipeline name">
			</div>
			
			<div class="form-group">
				<label for="inputContextName"></label>
				<input type="text" class="form-control" id="inputContextName" placeholder="context name">
			</div>
			
			<button type="button" id="btn1" class="btn btn-default">get Result (Ajax)</button>
			
		</form>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form role="form" action="" method="GET">
			
			<h2>show pipeline execution status</h2>
			
			<div class="form-group">
				<label for="inputExecutionId_2"></label>
				<input type="text" class="form-control" id="inputExecutionId_2" placeholder="executionId">
			</div>
			
			<button type="button" id="btn2" class="btn btn-default">get Result (Ajax)</button>
			
		</form>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form role="form" action="" method="GET">
			
			<h2>stop pipeline execution</h2>
			
			<div class="form-group">
				<label for="inputExecutionId_3"></label>
				<input type="text" class="form-control" id="inputExecutionId_3" placeholder="executionId">
			</div>
			
			<button type="button" id="btn3" class="btn btn-default">get Result (Ajax)</button>
			
		</form>
	</div>
</div>

<h2>Result:</h2>

<div id="result">
	<pre></pre>
</div>

</t:template>
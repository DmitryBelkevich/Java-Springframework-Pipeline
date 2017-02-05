$(document).ready(function() {
	$("#btn1").click(function() {
		var id = $("#inputPipelineId").val();
		
		$.ajax({
			url: "execute/execute_pipeline",
			contentType : "application/json; charset=UTF-8",
			type : "POST",
			data : id,
			success : function(data) {
				$("#result pre").text(data);
			},
			error : function(xhr, status, errorThrown) {
				alert("request failed with status: " + status + ". " + errorThrown);
			}
		});
	});
	
	$("#btn2").click(function() {
		var id = $("#inputExecutionId_2").val();
		
		$.ajax({
			url: "execute/pipeline_execution_status",
			contentType : "application/json; charset=UTF-8",
			type : "POST",
			data : id,
			success : function(data) {
				$("#result pre").text(data);
			},
			error : function(xhr, status, errorThrown) {
				alert("request failed with status: " + status + ". " + errorThrown);
			}
		});
	});
	
	$("#btn3").click(function() {
		alert("this function still not work");
	});
});
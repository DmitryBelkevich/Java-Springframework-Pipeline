package com.hard.models;

import java.util.ArrayList;
import java.util.List;

import com.hard.enums.Status;

public class PipelineExecutionStatus {
	private long executionId;
	private String pipeline;
	private Status status;
	private String startTime;
	private List<TaskExecutionStatus> tasks = new ArrayList<>();
	
	public void setExecutionId(long executionId) {
		this.executionId = executionId;
	}
	
	public long getExecutionId() {
		return executionId;
	}
	
	public void setPipeline(String pipeline) {
		this.pipeline = pipeline;
	}
	
	public String getPipeline() {
		return pipeline;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setTasks(List<TaskExecutionStatus> tasks) {
		this.tasks = tasks;
	}
	
	public List<TaskExecutionStatus> getTasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		return "PipelineExecutionStatus["
			+ "executionId=" + executionId
			+ ", pipeline=" + pipeline
			+ ", status=" + status
			+ ", startTime=" + startTime
			+ ", tasks=" + tasks
			+ "]";
	}
}
package com.hard.models;

import com.hard.enums.Status;

public class TaskExecutionStatus extends AbstractModel {
	private String name;
	private Status status;
	private String startTime;
	private String endTime;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	@Override
	public String toString() {
		return "TaskExecutionStatus["
			+ "name=" + name
			+ ", status=" + status
			+ ", startTime=" + startTime
			+ ", endTime=" + endTime
			+ "]";
	}
}
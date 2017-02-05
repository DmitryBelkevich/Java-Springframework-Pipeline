package com.hard.models;

import com.hard.enums.Status;
import com.hard.utils.Date;

public class Task extends AbstractModel {
	private String name;
	private String description;
	private Action action;
	
	private Status status;		// перенести данное поле в TaskExecutionStatus
	private String startTime;	// перенести данное поле в TaskExecutionStatus
	private String endTime;		// перенести данное поле в TaskExecutionStatus
	
	private TaskExecutionStatus taskExecutionStatus;	// перенести поля сюда
	
	public Task() {
		status = Status.PENDING;		// On start of execution all tasks initialized with status PENDING
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
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
	
	public Status execute() {
		startTime = Date.getCurrentTime();	// Log startTime to execution status
		status = Status.IN_PROGRESS;		// After start execution of task: Task status set to IN PROGRESS
		
		return action.execute(this);		// Result of task execution it is execution status
	}
	
	@Override
	public String toString() {
		return "Task["
				+ "id=" + id
				+ ", name=" + name
				+ ", description=" + description
				+ ", action=" + action
				+ "]";
	}
}
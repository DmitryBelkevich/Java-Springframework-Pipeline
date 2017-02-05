package com.hard.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hard.enums.Status;
import com.hard.utils.Date;

public class Pipeline extends AbstractModel {
	private String name;
	private String description;
	private List<Task> tasks = new ArrayList<>();	// таски сделать односвязным списком (каждый таск будет иметь массив ссылок на последующие таски)
	private Map<String, String> transitions;
	
	private Status status;				// перенести данное поле в PipelineExecutionStatus
	private String startTime;			// перенести данное поле в PipelineExecutionStatus
	private PipelineExecutionStatus pipelineExecutionStatus;	// перенести поля сюда
	
	public Pipeline() {
		status = Status.PENDING;
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
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTransitions(Map<String, String> transitions) {
		this.transitions = transitions;
	}
	
	public Map<String, String> getTransitions() {
		return transitions;
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
	
	public void execute() {
		startTime = Date.getCurrentTime();
		status = Status.IN_PROGRESS;		// After start execution of task: Pipeline execution status set to IN PROGRESS
		
		// добавить multi-threading, чтобы таски могли выполняться одновременно 
		for (Task task : tasks) {	// сделать так, чтобы таски выполнялись по принципу Chain of Responsibility
			Status finishStatus = task.execute();
			System.out.println("\t" + finishStatus);
			if (finishStatus.equals(Status.FAILED)) {	// If task action finished with status FAILED
				task.setStatus(Status.FAILED);			// Set task status to FAILED
				status = Status.FAILED;					// Set pipeline execution status to FAILED
				return;									// Break pipeline execution
			} else if (finishStatus.equals(Status.COMPLETED) || finishStatus.equals(Status.SKIPPED)) {// If current task action finished with result COMPLETED or SKIPPED:
				task.setEndTime(Date.getCurrentTime());	// EndTime logged to task status
				
				// Starts execution of next task according to transitions in pipeline
				// If task has transitions to several tasks ("Test" in example) then these tasks should be executed in parallel
				// If one task depends on several ("SYNC" in example) this task should be executed only after dependant tasks completed
			}
			
			// If there is no next task finish execution
			// Log endTime to execution status
		}
		
		// здесь будет логика последовательности вполнения тасков
		// будет зависеть от transitions
	}
	
	@Override
	public String toString() {
		return "Pipeline["
				+ "id=" + id
				+ ", name=" + name
				+ ", description=" + description
				+ ", tasks=" + tasks
				+ ", transitions=" + transitions
				+ "]";
	}
}
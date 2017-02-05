package com.hard.models;

import com.hard.enums.Status;
import com.hard.enums.Type;

public class Action extends AbstractModel {
	private Type type;
	
	private Status status;
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public Status execute(Task task) {
		//Status status = null;
		
		switch (type) {
		case PRINT:
			status = print(task);
			break;
		case RANDOM:
			status = random(task);
			break;
		case COMPLETED:
			status = completed(task);
			break;
		case DELAYED:
			status = delayed(task);
			break;
		default:
			break;
		}
		
		return status;
	}
	
	/* Actions */
	
	/**
	 * prints task name to console and sets tasks status as COMPLETED
	 */
	public Status print(Task task) {
		System.out.println("task name: " + task.getName());
		
		Status resultStatus = Status.COMPLETED;
		
		task.setStatus(resultStatus);
		
		return resultStatus;
	}
	
	/**
	 * prints task name to console, sleep thread for one second
	 * and sets tasks status to one of random value (COMPLETED, SKIPPED, FAILED, IN PROGRESS)
	 */
	public Status random(Task task) {
		System.out.println("task name: " + task.getName());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Status resultStatus = Status.getRandom();
		
		task.setStatus(resultStatus);
		
		return resultStatus;
	}
	
	/**
	 * prints task name to console, sleep thread for one second
	 * and sets tasks status to COMPLETED
	 */
	public Status completed(Task task) {
		System.out.println("task name: " + task.getName());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Status resultStatus = Status.COMPLETED;
		
		task.setStatus(resultStatus);
		
		return resultStatus;
	}
	
	/**
	 * prints task name to console, sleep thread for 10 second
	 * and sets tasks status to COMPLETED
	 */
	public Status delayed(Task task) {
		System.out.println("task name: " + task.getName());
		
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Status resultStatus = Status.COMPLETED;
		
		task.setStatus(resultStatus);
		
		return resultStatus;
	}
	
	@Override
	public String toString() {
		return "Action["
				+ "id=" + id
				+ ", type=" + type
				+ ", status=" + status
				+ "]";
	}
}
package com.hard.models;

public abstract class AbstractModel {
	protected long id;
	
	public AbstractModel() {
		
	}
	
	public AbstractModel(long id) {
		this.id = id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
}
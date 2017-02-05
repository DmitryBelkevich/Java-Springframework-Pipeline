package com.hard.dao;

import java.util.List;

import com.hard.models.PipelineExecutionStatus;

public interface PipelineExecutionStatusDao {
	public List<PipelineExecutionStatus> getAll();
	public PipelineExecutionStatus getById(long id);
	public void add(PipelineExecutionStatus model);
	public void update(PipelineExecutionStatus model);
	public void delete(PipelineExecutionStatus model);
}
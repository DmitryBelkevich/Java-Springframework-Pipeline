package com.hard.dao;

import java.util.List;

import com.hard.models.Pipeline;

public interface PipelineDao {
	public List<Pipeline> getAll();
	public Pipeline getById(long id);
	public void add(Pipeline model);
	public void update(Pipeline model);
	public void delete(Pipeline model);
}
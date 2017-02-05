package com.hard.services;

import java.util.List;

import com.hard.models.Pipeline;

public interface PipelineDatabaseService {
	public List<Pipeline> getAll();
	public Pipeline getById(long id);
	public void add(Pipeline model);
	public void update(Pipeline model);
	public void delete(Pipeline model);
}
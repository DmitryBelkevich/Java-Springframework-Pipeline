package com.hard.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hard.dao.PipelineExecutionStatusDatabaseDao;
import com.hard.models.PipelineExecutionStatus;
import com.hard.services.PipelineExecutionStatusDatabaseService;

@Service("pipelineExecutionStatusDatabaseService")
@Transactional
public class PipelineExecutionStatusDatabaseServiceImpl implements PipelineExecutionStatusDatabaseService {
	@Autowired
	@Qualifier("pipelineExecutionStatusDatabaseDao")
	PipelineExecutionStatusDatabaseDao pipelineExecutionStatusDatabaseDao;
	
	@Override
	public List<PipelineExecutionStatus> getAll() {
		return pipelineExecutionStatusDatabaseDao.getAll();
	}
	
	@Override
	public PipelineExecutionStatus getById(long id) {
		return pipelineExecutionStatusDatabaseDao.getById(id);
	}
	
	@Override
	public void add(PipelineExecutionStatus model) {
		pipelineExecutionStatusDatabaseDao.add(model);
	}
	
	@Override
	public void update(PipelineExecutionStatus model) {
		pipelineExecutionStatusDatabaseDao.update(model);
	}
	
	@Override
	public void delete(PipelineExecutionStatus model) {
		pipelineExecutionStatusDatabaseDao.delete(model);
	}
}
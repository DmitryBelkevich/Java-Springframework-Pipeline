package com.hard.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hard.dao.impl.PipelineDatabaseDaoImpl;
import com.hard.dao.impl.PipelineFileDaoImpl;
import com.hard.models.Pipeline;
import com.hard.services.PipelineDatabaseService;

@Service("pipelineDatabaseService")
@Transactional
public class PipelineDatabaseServiceImpl implements PipelineDatabaseService {
	@Autowired
	@Qualifier("pipelineDatabaseDao")
	private PipelineDatabaseDaoImpl pipelineDatabaseDao;
	
	@Autowired
	@Qualifier("pipelineFileDao")
	private PipelineFileDaoImpl pipelineFileDao;
	
	@Override
	public List<Pipeline> getAll() {
		return pipelineDatabaseDao.getAll();
	}
	
	@Override
	public Pipeline getById(long id) {
		return pipelineDatabaseDao.getById(id);
	}
	
	@Override
	public void add(Pipeline model) {
		pipelineDatabaseDao.add(model);
	}
	
	@Override
	public void update(Pipeline model) {
		pipelineDatabaseDao.update(model);
	}
	
	@Override
	public void delete(Pipeline model) {
		pipelineDatabaseDao.delete(model);
	}
}
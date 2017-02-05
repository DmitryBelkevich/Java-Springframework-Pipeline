package com.hard.services.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hard.dao.impl.PipelineFileDaoImpl;
import com.hard.models.Pipeline;
import com.hard.services.PipelineFileService;

@Service("pipelineFileService")
public class PipelineFileServiceImpl implements PipelineFileService {
	@Autowired
	@Qualifier("pipelineFileDao")
	private PipelineFileDaoImpl pipelineFileDao;

	@Override
	public List<Pipeline> getAllByFolderName(String folderName) {
		return pipelineFileDao.getAllByFolderName(folderName);
	}
	
	@Override
	public List<String> getAllPathsByFolderName(String folderName) {
		return pipelineFileDao.getAllPathsByFolderName(folderName);
	}

	@Override
	public Pipeline getByFileName(String fileName) {
		return pipelineFileDao.getByFileName(fileName);
	}
	
	@Override
	public void addFile(File file) {
		pipelineFileDao.addFile(file);
	}
	
	@Override
	public void updateFile(File file) {
		pipelineFileDao.updateFile(file);
	}
	
	@Override
	public void deleteFile(File file) {
		pipelineFileDao.deleteFile(file);
	}
}
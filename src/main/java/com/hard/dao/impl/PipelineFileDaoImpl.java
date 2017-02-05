package com.hard.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hard.dao.PipelineFileDao;
import com.hard.models.Pipeline;
import com.hard.utils.YamlSerializator;

@Repository("pipelineFileDao")
public class PipelineFileDaoImpl implements PipelineFileDao {
	@Override
	public List<Pipeline> getAllByFolderName(String absolutePath) {
		List<Pipeline> pipelines = new ArrayList<>();
		
		File folder = new File(absolutePath);
		
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String filePath = absolutePath + "/" + listOfFiles[i].getName();
					Pipeline pipeline = getByFileName(filePath);
					pipelines.add(pipeline);
				}
			}
		}
		
		return pipelines;
	}
	
	@Override
	public List<String> getAllPathsByFolderName(String absolutePath) {
		List<String> paths = new ArrayList<>();
		
		File folder = new File(absolutePath);
		
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String filePath = absolutePath + "/" + listOfFiles[i].getName();
					paths.add(filePath);
				}
			}
		}
		
		return paths;
	}
	
	@Override
	public Pipeline getByFileName(String absolutePath) {
		File file = new File(absolutePath);
		
		Pipeline pipeline = (Pipeline) YamlSerializator.yamlFileToObject(file, Pipeline.class);
		
		return pipeline;
	}
	
	@Override
	public void addFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateFile(File file) {
		File fileOld = new File(file.getAbsolutePath());
		fileOld.delete();
		
		addFile(file);
	}
	
	@Override
	public void deleteFile(File file) {
		file.delete();
	}
}
package com.hard.dao;

import java.io.File;
import java.util.List;

import com.hard.models.Pipeline;

public interface PipelineFileDao {
	public List<Pipeline> getAllByFolderName(String folderName);
	public List<String> getAllPathsByFolderName(String folderName);
	
	public Pipeline getByFileName(String fileName);
	public void addFile(File file);
	public void updateFile(File file);
	public void deleteFile(File file);
}
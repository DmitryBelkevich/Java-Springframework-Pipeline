package com.hard.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hard.models.Pipeline;
import com.hard.models.PipelineExecutionStatus;
import com.hard.services.PipelineDatabaseService;
import com.hard.services.PipelineExecutionStatusDatabaseService;
import com.hard.services.PipelineFileService;
import com.hard.utils.YamlSerializator;

@Controller
@RequestMapping(value = "/execute")
public class ExecuteController {
	@Autowired
	@Qualifier("pipelineExecutionStatusDatabaseService")
	PipelineExecutionStatusDatabaseService pipelineExecutionStatusDatabaseService;
	
	@Autowired
	@Qualifier("pipelineFileService")
	private PipelineFileService pipelineFileService;
	
	@Autowired
	@Qualifier("pipelineDatabaseService")
	private PipelineDatabaseService pipelineDatabaseService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main() {
		return "execute/main";
	}
	
	@RequestMapping(value = "/execute_pipeline", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	@ResponseBody
	public String executePipeline(@RequestBody String id) {
		PipelineExecutionStatus pipelineExecutionStatus = null;
		try {
			// execute pipeline
			Pipeline pipeline = pipelineDatabaseService.getById(Long.parseLong(id));
			pipeline.execute();
			
			pipelineExecutionStatus = pipelineExecutionStatusDatabaseService.getById(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
			return "bad request";
		}
		
		File file = new File("C:/temp.yml");
		
		YamlSerializator.objectToYamlFile(file, pipelineExecutionStatus);
		
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader("C:/temp.yml"));
			
			while ((sCurrentLine = br.readLine()) != null)
				result.append(sCurrentLine).append("\n");
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pipelineFileService.deleteFile(file);
		
		return result.toString();
	}
	
	@RequestMapping(value = "/pipeline_execution_status", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	@ResponseBody
	public String pipelineExecutionStatus(@RequestBody String id) {
		PipelineExecutionStatus pipelineExecutionStatus = null;
		try {
			pipelineExecutionStatus = pipelineExecutionStatusDatabaseService.getById(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
			return "bad request";
		}
		
		File file = new File("C:/temp.yml");
		
		YamlSerializator.objectToYamlFile(file, pipelineExecutionStatus);
		
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader("C:/temp.yml"));
			
			while ((sCurrentLine = br.readLine()) != null)
				result.append(sCurrentLine).append("\n");
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pipelineFileService.deleteFile(file);
		
		return result.toString();
	}
}
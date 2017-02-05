package com.hard.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hard.models.PipelineExecutionStatus;
import com.hard.services.PipelineExecutionStatusDatabaseService;
import com.hard.utils.YamlSerializator;

@Controller
@RequestMapping(value = "/status")
@PropertySource("classpath:config.properties")
public class StatusController {
	@Autowired
	@Qualifier("pipelineExecutionStatusDatabaseService")
	PipelineExecutionStatusDatabaseService pipelineExecutionStatusDatabaseService;
	
	@Value("${app.statusesPath}")
	private String statusesPath;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getFromDB(ModelMap modelMap) {
		List<PipelineExecutionStatus> pipelineExecutionStatuses = pipelineExecutionStatusDatabaseService.getAll();
		
		modelMap.addAttribute("pipelineExecutionStatuses", pipelineExecutionStatuses);
		
		return "status/main";
	}
	
	@RequestMapping(value = "/toFile/{id}", method = RequestMethod.GET)
	public String toFile(@PathVariable("id") long id) {
		PipelineExecutionStatus pipelineExecutionStatus = pipelineExecutionStatusDatabaseService.getById(id);
		YamlSerializator.objectToYamlFile(new File(statusesPath), pipelineExecutionStatus);
		
		return "redirect:/status";
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String getFromDB(@PathVariable("id") long id, ModelMap modelMap) {
		PipelineExecutionStatus pipelineExecutionStatus = pipelineExecutionStatusDatabaseService.getById(id);
		
		modelMap.addAttribute("pipelineExecutionStatus", pipelineExecutionStatus);
		
		return "status/get";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addToDB() {
		File file = new File(statusesPath);
		PipelineExecutionStatus pipelineExecutionStatus = (PipelineExecutionStatus) YamlSerializator.yamlFileToObject(file, PipelineExecutionStatus.class);
		
		pipelineExecutionStatusDatabaseService.add(pipelineExecutionStatus);
		
		return "redirect:/status";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateDB(@PathVariable("id") long id) {
		pipelineExecutionStatusDatabaseService.update(pipelineExecutionStatusDatabaseService.getById(id));
		
		return "redirect:/status";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delFromDB(@PathVariable("id") long id) {
		pipelineExecutionStatusDatabaseService.delete(pipelineExecutionStatusDatabaseService.getById(id));
		
		return "redirect:/status";
	}
}
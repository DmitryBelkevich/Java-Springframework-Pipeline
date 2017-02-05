package com.hard.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.helpers.UriMatcher;
import com.hard.models.Pipeline;
import com.hard.services.PipelineDatabaseService;
import com.hard.services.PipelineFileService;

@Controller
@RequestMapping(value = "/pipeline")
@PropertySource("classpath:config.properties")
public class PipelineController {
	@Autowired
	@Qualifier("pipelineDatabaseService")
	private PipelineDatabaseService pipelineDatabaseService;
	
	@Autowired
	@Qualifier("pipelineFileService")
	private PipelineFileService pipelineFileService;
	
	@Value("${app.pipelinesPath}")
	private String pipelinesPath;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(ModelMap modelMap) {
		try {
			List<Pipeline> filePipelines = pipelineFileService.getAllByFolderName(pipelinesPath);
			modelMap.addAttribute("filePipelines", filePipelines);
			
			List<String> pathsPipelines = pipelineFileService.getAllPathsByFolderName(pipelinesPath);
			modelMap.addAttribute("pathsPipelines", pathsPipelines);
			
			List<Pipeline> databasePipelines = pipelineDatabaseService.getAll();
			modelMap.addAttribute("databasePipelines", databasePipelines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "pipeline/main";
	}
	
	@RequestMapping(value = "/file_start/**", method = RequestMethod.GET)
	public String fileStart(HttpServletRequest request) {
		try {
			String filePath = UriMatcher.cutStart(request);
			
			Pipeline pipeline = pipelineFileService.getByFileName(filePath);
			
			pipeline.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/file_add/**", method = RequestMethod.GET)
	public String fileAdd(HttpServletRequest request) {
		try {
			String filePath = UriMatcher.cutAdd(request);
			
			Pipeline pipeline = pipelineFileService.getByFileName(filePath);
			
			pipelineDatabaseService.add(pipeline);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/file_delete/**", method = RequestMethod.GET)
	public String fileDelete(HttpServletRequest request) {
		try {
			String filePath = UriMatcher.cutDelete(request);
			
			pipelineFileService.deleteFile(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/database_start/{id}", method = RequestMethod.GET)
	public String databaseStart(@PathVariable("id") int id) {
		try {
			Pipeline pipeline = pipelineDatabaseService.getById(id);
			
			pipeline.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/database_edit/{id}", method = RequestMethod.GET)
	public String databaseEdit(@PathVariable("id") int id, ModelMap modelMap) {
		try {
			Pipeline pipeline = pipelineDatabaseService.getById(id);
			
			modelMap.addAttribute("pipeline", pipeline);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "pipeline/get";
	}
	
	@RequestMapping(value = "/database_update/{id}", method = RequestMethod.GET)
	public String updateDelete(@PathVariable("id") int id) {
		try {
			Pipeline pipeline = pipelineDatabaseService.getById(id);
			
			pipelineDatabaseService.update(pipeline);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/database_delete/{id}", method = RequestMethod.GET)
	public String databaseDelete(@PathVariable("id") int id) {
		try {
			pipelineDatabaseService.delete(pipelineDatabaseService.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
	
	@RequestMapping(value = "/new", method = {RequestMethod.GET, RequestMethod.POST})
	public String newPipeline(@RequestParam("context") String context) {
		File file = new File("C:/temp.yml");
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("C:/temp.yml"));
			out.write(context);
			out.close();
		} catch (IOException e) {
			System.out.println("Exception");
		}
		
		try {
			Pipeline pipeline = pipelineFileService.getByFileName("C:/temp.yml");
			
			pipelineFileService.deleteFile(file);
			
			pipelineDatabaseService.add(pipeline);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/pipeline";
	}
}
package com.hard.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriTemplate;

public class UriMatcher {
	public static String cutStart(HttpServletRequest request) {
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    UriTemplate template = new UriTemplate("/pipeline/file_start/{value}");        
	    boolean isTemplateMatched = template.matches(restOfTheUrl);
	    if (isTemplateMatched) {
			Map<String, String> matchTemplate = new HashMap<String, String>();
			matchTemplate = template.match(restOfTheUrl);
			String urlTail = matchTemplate.get("value");
			return urlTail;
	    }
	    
		return null;
	}
	
	public static String cutAdd(HttpServletRequest request) {
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    UriTemplate template = new UriTemplate("/pipeline/file_add/{value}");        
	    boolean isTemplateMatched = template.matches(restOfTheUrl);
	    if (isTemplateMatched) {
			Map<String, String> matchTemplate = new HashMap<String, String>();
			matchTemplate = template.match(restOfTheUrl);
			String urlTail = matchTemplate.get("value");
			return urlTail;
	    }
	    
		return null;
	}
	
	public static String cutDelete(HttpServletRequest request) {
		//String urlTail = new AntPathMatcher().extractPathWithinPattern("000/pipeline/deleteFile/**", request.getRequestURI());
		//System.out.println(urlTail);
		
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    UriTemplate template = new UriTemplate("/pipeline/file_delete/{value}");        
	    boolean isTemplateMatched = template.matches(restOfTheUrl);
	    if (isTemplateMatched) {
			Map<String, String> matchTemplate = new HashMap<String, String>();
			matchTemplate = template.match(restOfTheUrl);
			String urlTail = matchTemplate.get("value");
			return urlTail;
	    }
	    
		return null;
	}
}
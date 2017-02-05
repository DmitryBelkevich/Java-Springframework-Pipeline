package com.hard.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlSerializator {
	/*
	 * File
	 */
	public static Object yamlFileToObject(File file, Class<?> clazz) {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		
		Object object = null;
        try {
            object = objectMapper.readValue(file, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return object;
	}
	
	public static void objectToYamlFile(File file, Object object) {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		
		try {
			objectMapper.writeValue(file, object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
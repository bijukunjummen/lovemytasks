package org.bk.lmt.spring;

import org.bk.lmt.domain.Project;
import org.bk.lmt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToProjectConverter implements Converter<String, Project>{

	@Autowired private ProjectService projectService;
	
	@Override
	public Project convert(String source) {
		return this.projectService.findById(Long.valueOf(source));
	}
	
	

}

package org.bk.lmt.spring;

import org.bk.lmt.domain.GtdProject;
import org.bk.lmt.service.GtdProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;

@Configurable
public class StringToProjectConverter implements Converter<String, GtdProject>{

	@Autowired private GtdProjectService projectService;
	
	@Override
	public GtdProject convert(String source) {
		return this.projectService.findById(Long.valueOf(source));
	}
	
	

}

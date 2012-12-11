package org.bk.lmt.spring;

import org.bk.lmt.domain.Context;
import org.bk.lmt.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToContextConverter implements Converter<String, Context>{

	@Autowired private ContextService contextService;
	
	@Override
	public Context convert(String source) {
		return this.contextService.findById(Long.valueOf(source));
	}
	
	

}

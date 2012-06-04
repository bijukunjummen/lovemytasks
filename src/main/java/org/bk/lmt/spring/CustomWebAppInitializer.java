package org.bk.lmt.spring;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
//		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
//		rootContext.setConfigLocations(new String[]{"classpath*:META-INF/spring/applicationContext-security.xml", "classpath*:META-INF/spring/applicationContext.xml"});
//
//		container.addListener(new ContextLoaderListener(rootContext));
//
//		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", DispatcherServlet.class);
//		dispatcher.setInitParameter("contextConfigLocation", "/WEB-INF/spring/webmvc-config.xml");
//		dispatcher.addMapping("/");
//
//		container.addFilter("Spring OpenEntityManagerInViewFilter", org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class)
//			.addMappingForUrlPatterns(null, false, "/*");
//
//		container.addFilter("HttpMethodFilter", org.springframework.web.filter.HiddenHttpMethodFilter.class)
//			.addMappingForUrlPatterns(null, false, "/*");
//
//		container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
//					.addMappingForUrlPatterns(null, false, "/*");
//
//		FilterRegistration charEncodingfilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
//		charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
//		charEncodingfilterReg.setInitParameter("forceEncoding", "true");
//		charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");
	}
}
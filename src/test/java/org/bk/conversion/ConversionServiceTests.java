package org.bk.conversion;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ConversionServiceTests {
	@Autowired ConversionService conversionService;
	@Test
	public void test() {
		
		Date date = this.conversionService.convert(1348343828187L, Date.class);
		
		System.out.println(date);
	}


	@Configuration
	public static class ConversionConfig{
		
		@Bean
		public FormattingConversionServiceFactoryBean conversionService(){
			return new FormattingConversionServiceFactoryBean();
		}
		
	}
}

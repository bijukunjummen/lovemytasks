package org.bk.lmt.web.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper(){
        super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    
}

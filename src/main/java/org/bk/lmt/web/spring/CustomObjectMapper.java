package org.bk.lmt.web.spring;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper(){
        super.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        super.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
}

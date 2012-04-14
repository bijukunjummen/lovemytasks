package org.bk.lmt.web.spring;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper(){
        super.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}

package tw.com.tendebank.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor{  
	
    private DateFormat dateFormat;
    
    /**
     *  預設Constructor
     */
    public DateJsonValueProcessor() {  
        dateFormat = new SimpleDateFormat(DateUtils.DATE_PATTERN_YMD_DESH);
    }
  
    /** 
     *  帶參數Constructor
     */  
    public DateJsonValueProcessor(String datePattern) {  
        try {  
            dateFormat = new SimpleDateFormat(datePattern);  
        } catch (Exception e) {  
            dateFormat = new SimpleDateFormat(DateUtils.DATE_PATTERN_YMD_DESH);   
        }  
    }
  
    /* 
     * (non-Javadoc) 
     * @see 
     * net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang 
     * .Object, net.sf.json.JsonConfig) 
     */  
    @Override  
    public Object processArrayValue(Object obj, JsonConfig jsonConfig) {  
        return process(obj);  
    }  
  
    /* 
     * (non-Javadoc) 
     * @see 
     * net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang 
     * .String, java.lang.Object, net.sf.json.JsonConfig) 
     */  
    @Override  
    public Object processObjectValue(String key, Object obj, JsonConfig jsonConfig) {  
        return process(obj);  
    }  
  
    private Object process(Object obj) {  
        if (obj == null) {  
            return "";  
        } else {
            return dateFormat.format((Date) obj);  
        } 
    }  
  
}


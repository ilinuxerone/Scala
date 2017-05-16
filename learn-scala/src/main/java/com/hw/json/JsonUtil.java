package com.hw.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.domain.BookDO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class JsonUtil {
    public static Object jsonToBeanByJackson(String json, Object clazz, String dataFormatString) {
        if (null == json || json.length() == 0 || clazz == null) {
            return null;
        }
        Object object = null;
        ObjectMapper mapper = new ObjectMapper();
        // if(StringUtils.isNotBlank(dataFormatString)){
        mapper.setDateFormat(new SimpleDateFormat(dataFormatString));
        //  }

        try {
            object = mapper.readValue(json, clazz.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return object;
    }

    public static String beanToJsonByJackson(Object object,String dataFormatString){
        if(object != null){
            ObjectMapper mapper = new ObjectMapper();
           // if(StringUtils.isNotBlank(dataFormatString)){
                mapper.setDateFormat(new SimpleDateFormat(dataFormatString));
           // }
            String json = null;

            try {
                json = mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return json;
        }else{
            return null;
        }
    }

    public static String stringToJsonByJackson(String key,String value){
/*        if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
            return null;
        }*/
        Map<String,String> map = new HashMap<String,String>();
        map.put(key, value);
        return beanToJsonByJackson(map,null);
    }

    public static void main(String[] args) {
        //时间不转换就是long值
        BookDO entity = new BookDO();
        entity.setDate(new Date());
        System.out.println(beanToJsonByJackson(entity,"yyyy-MM-dd HH:mm:ss"));

        String json = "{\"author\":\"hll\",\"content\":\"ES即etamsports\",\"id\":\"693\",\"date\":\"2017-04-12 08:40:36\",\"title\":\"百度百科\"}";
        

        BookDO entity3  = (BookDO)jsonToBeanByJackson(json, new BookDO(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(entity3.getDate());

        System.out.println("--------------------------------");

        //new Date
        System.out.println(new Date());
    }
}

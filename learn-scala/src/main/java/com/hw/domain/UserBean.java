package com.hw.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/17.
 */
public class UserBean{
    //关于序列化与反序列化时json中字段名称的问题
    @JsonProperty("firstName")
    private String first_name;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"firstName\":\"laowang\"}";
        //默认开启注解模式，也就是下面UserBean中的@JsonProperty("firstName")是起效果的
        ObjectMapper mapper = new ObjectMapper();
        try {
            //成功的将json 转化为了 userBean
            UserBean userBean = mapper.readValue(jsonStr, UserBean.class);

            //忽略注解，使得原来业务中的字段不变，也就还是：{first_name:laowang}，如果不设置，则返回{firstName:laowang}
            mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            System.out.println(mapper.writeValueAsString(userBean));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
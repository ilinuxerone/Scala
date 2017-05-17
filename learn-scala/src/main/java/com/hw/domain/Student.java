package com.hw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/17.
 */
class Student {
    @JsonIgnore
    private int studentId;

    public Student() {}

    @JsonProperty("studentId")
    public int getStudentId() {
        return studentId;
    }

    @JsonProperty("student_id")
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"student_id\":11}";
        //默认开启注解模式，也就是下面UserBean中的@JsonProperty("firstName")是起效果的
        ObjectMapper mapper = new ObjectMapper();
        try {
            //成功的将json 转化为了 userBean
            Student userBean = mapper.readValue(jsonStr, Student.class);

            //忽略注解，使得原来业务中的字段不变，也就还是：{first_name:laowang}，如果不设置，则返回{firstName:laowang}
            //mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            System.out.println(mapper.writeValueAsString(userBean));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
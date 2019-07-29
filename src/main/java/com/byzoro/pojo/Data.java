package com.byzoro.pojo;

import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class Data implements NameValuePair {
    private String id;

    private String name;

//    private List<HashMap<String,Object>> list;
//
//    public List<HashMap<String, Object>> getList() {
//        return list;
//    }
//
//    public void setList(List<HashMap<String, Object>> list) {
//        this.list = list;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

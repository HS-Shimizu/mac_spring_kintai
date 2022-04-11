package com.example.demo.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DataBean {

	String year;
	String month;
	String ymd;
	String workst;
	String worked;
	String workrt;
	String lastDay;
	
	 /** 月のMapオブジェクト */
    public Map<String,String> getMonthItems(){
        Map<String, String> monthMap = new LinkedHashMap<String, String>();
        for(int i = 1; i <= 12; i++){
            monthMap.put(String.valueOf(i), String.valueOf(i));
        }
        return monthMap;
    }
}

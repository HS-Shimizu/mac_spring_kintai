package com.example.demo.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class User {
	
	private String year;
	private String month;
	private String lastDay;
	private String ymd ;
	

	
	 /** 月のMapオブジェクト */
    public Map<String,String> getMonthItems(){
        Map<String, String> monthMap = new LinkedHashMap<String, String>();
        for(int i = 1; i <= 12; i++){
            monthMap.put(String.valueOf(i), String.valueOf(i));
        }
        return monthMap;
    }
    
   
}

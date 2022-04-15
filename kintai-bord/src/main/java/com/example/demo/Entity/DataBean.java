package com.example.demo.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class DataBean {

	@NotNull
	@Size(min = 3, max = 4)
	String year;
	String month;
	String ymd;
	@Size(min = 4, max = 4)
	String workst;
	@Size(min = 4, max = 4)
	String worked;
	@Size(min = 4, max = 4)
	String workrt;
	String lastDay;
//	@Valid
//	List<kintai> kintaiList;

	/** 月のMapオブジェクト */
	public Map<String, String> getMonthItems() {
		Map<String, String> monthMap = new LinkedHashMap<String, String>();
		for (int i = 1; i <= 12; i++) {
			monthMap.put(String.valueOf(i), String.valueOf(i));
		}
		return monthMap;
	}
}

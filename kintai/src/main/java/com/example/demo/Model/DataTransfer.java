package com.example.demo.Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Entity.User;

import lombok.Data;

@Data
public class DataTransfer {

	/**
	  * ユーザー情報リスト
	  */
	private List<User> dataList;
	private String year;
	private String month;

	/** 月のMapオブジェクト */
	public Map<String, String> getMonthItems() {
		Map<String, String> monthMap = new LinkedHashMap<String, String>();
		for (int i = 1; i <= 12; i++) {
			monthMap.put(String.valueOf(i), String.valueOf(i));
		}
		return monthMap;
	}
}

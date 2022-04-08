package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Entity.User;
import com.example.demo.Model.DataTransfer;

public class Service {

	@ModelAttribute
	public static DataTransfer setData() {

		User user = new User();
		
		//現在の年取得
		GregorianCalendar gcalendar = new GregorianCalendar();
		String sYear = String.valueOf(gcalendar.get(Calendar.YEAR));
		

		//現在の月取得
		gcalendar = new GregorianCalendar();
		String sMonth = String.valueOf(gcalendar.get(Calendar.MONTH) + 1);
		

		//月末の日付取得処理
		setDay(user,sYear,sMonth);

		int lastDay = Integer.valueOf(user.getLastDay());
		List<User> list = new ArrayList<User>();
		
		for (int i = 1; i <= lastDay; i++) {
			
			String ymd = sYear + "/" + sMonth + "/" + i;
			
			
			user = new User();
			user.setYear(sYear);
			user.setMonth(sMonth);
			user.setYmd(ymd);
			
			list.add(user);
		}
		DataTransfer data = new DataTransfer();
		data.setYear(sYear);
		data.setMonth(sMonth);
		data.setDataList(list);
		
		return data;
	}
	
	@ModelAttribute
	public static User setUser(User user) {

		//現在の年取得
		GregorianCalendar gcalendar = new GregorianCalendar();
		String sYear = String.valueOf(gcalendar.get(Calendar.YEAR));
		user.setYear(sYear);
		

		//現在の月取得
		gcalendar = new GregorianCalendar();
		String sMonth = String.valueOf(gcalendar.get(Calendar.MONTH) + 1);
		user.setMonth(sMonth);
		
		return user;
	}

	@ModelAttribute
	public static User setData(User user, String sYear, String sMonth) {

		//入力された年を設定
		GregorianCalendar gcalendar = new GregorianCalendar();
		user.setYear(sYear);

		//入力された月を設定
		gcalendar = new GregorianCalendar();
		user.setMonth(sMonth);

		setDay(user,sYear,sMonth);
		
		return user;
	}

	public static void setDay(User user, String sYear, String sMonth) {
		//月末の日付取得処理
		Calendar calendar = Calendar.getInstance();
		int year = Integer.valueOf(sYear);
		int month = Integer.valueOf(sMonth);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String sResult = String.valueOf(result);
		user.setLastDay(sResult);
	}

}

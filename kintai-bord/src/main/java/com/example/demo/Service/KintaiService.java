package com.example.demo.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.demo.Entity.DataBean;

public class KintaiService {

	public static DataBean setData(DataBean data) {
		
		//現在の年取得
		GregorianCalendar gcalendar = new GregorianCalendar();
		String sYear = String.valueOf(gcalendar.get(Calendar.YEAR));

		//現在の月取得
		gcalendar = new GregorianCalendar();
		String sMonth = String.valueOf(gcalendar.get(Calendar.MONTH) + 1);
		
		data.setYear(sYear);
		data.setMonth(sMonth);
		
		return data;
	}
}

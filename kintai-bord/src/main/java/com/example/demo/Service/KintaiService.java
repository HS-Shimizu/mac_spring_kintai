package com.example.demo.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.demo.Entity.DataBean;

public class KintaiService {

	public static DataBean setData(DataBean data) {

		//現在の年取得
		GregorianCalendar gcalendar = new GregorianCalendar();

		int year = gcalendar.get(Calendar.YEAR);

		String sYear = String.valueOf(year);

		//現在の月取得
		gcalendar = new GregorianCalendar();
		int month = gcalendar.get(Calendar.MONTH) + 1;

		String sMonth = String.valueOf(month);

		//現在の最終日取得
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);

		int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String sResult = String.valueOf(result);

		data.setYear(sYear);
		data.setMonth(sMonth);
		data.setLastDay(sResult);

		return data;
	}

	public static List<DataBean> display(List<DataBean> dataList, DataBean data) {

		String sYear = data.getYear();
		String sMonth = data.getMonth();
		String lastDay = data.getLastDay();

		for (int i = 1; i <= Integer.valueOf(lastDay); i++) {

			String ymd = data.getYear() + "/" + data.getMonth() + "/" + i;

			data = new DataBean();
			
			data.setYear(sYear);
			data.setMonth(sMonth);
			data.setYmd(ymd);
			
			dataList.add(data);
		}

		return dataList;

	}

}

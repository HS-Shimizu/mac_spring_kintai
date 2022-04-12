package com.example.demo.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DataBean;
import com.example.demo.Entity.kintai;
import com.example.demo.Repository.KintaiRepository;

@Service
public class KintaiService {

	@Autowired
	KintaiRepository kintaiRepository;
	GregorianCalendar gcalendar = new GregorianCalendar();

	/**
	 * 現在の年を取得
	 */
	public void getYear(DataBean data) {

		//現在の年取得
		int year = gcalendar.get(Calendar.YEAR);
		String sYear = String.valueOf(year);
		data.setYear(sYear);
	}

	/**
	 * 現在の月を取得
	 */
	public void getMonth(DataBean data) {

		//現在の月取得
		int month = gcalendar.get(Calendar.MONTH) + 1;
		String sMonth = String.valueOf(month);
		data.setMonth(sMonth);
	}

	/**
	 * 年　月　最終日をセット
	 * 
	 */
	public DataBean setData(DataBean data) {

		//現在の年取得
		getYear(data);
		//現在の月取得
		getMonth(data);
		//最終日の取得
		setLastData(data, data.getYear(), data.getMonth());

		return data;
	}
	/**
	 * 年　月　最終日をセット (2回目用）
	 * 
	 */
	public DataBean setData(DataBean data, String year, String month) {

		//現在の年取得
		data.setYear(year);
		//現在の月取得
		data.setMonth(month);
		//最終日の取得
		setLastData(data, year, month);

		return data;
	}
	

	/**
	 * 最終日の取得
	 * 
	 */
	public void setLastData(DataBean data, String sYear, String sMonth) {

		int year = Integer.valueOf(sYear);
		int month = Integer.valueOf(sMonth);
		//現在の最終日取得
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);

		int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String sResult = String.valueOf(result);

		data.setLastDay(sResult);
	}

	/**
	 * 表示用データのセット
	 * 
	 */
	public List<DataBean> display(List<DataBean> dataList, DataBean data) {

		String sYear = data.getYear();
		String sMonth = data.getMonth();
		String lastDay = data.getLastDay();

		for (int i = 1; i <= Integer.valueOf(lastDay); i++) {

			String year = data.getYear();
			String month = data.getMonth();
			String day = String.valueOf(i);

			year = String.format("%4s", year).replace(" ", "0");
			month = String.format("%2s", month).replace(" ", "0");
			day = String.format("%2s", day).replace(" ", "0");

			String ymd = year + "/" + month + "/" + day;
			kintai kintailist = kintaiRepository.findByYmd(year + month + day);

			data = new DataBean();

			data.setYear(sYear);
			data.setMonth(sMonth);
			data.setYmd(ymd);
			if (kintailist == null) {

			} else {
				data.setWorkst(kintailist.getWork_st());
				data.setWorked(kintailist.getWork_ed());
				data.setWorkrt(kintailist.getWork_rt());
			}

			dataList.add(data);
		}

		return dataList;
	}

	public List<kintai> regist() {

		// 入力された値を探す
		//入力された値を登録
		return kintaiRepository.save(null);
	}
}

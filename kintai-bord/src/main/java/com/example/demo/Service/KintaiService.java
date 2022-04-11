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
	
	public DataBean setData(DataBean data) {

		//現在の年取得
		GregorianCalendar gcalendar = new GregorianCalendar();
		int year = gcalendar.get(Calendar.YEAR);
		String sYear = String.valueOf(year);

		//現在の月取得
		gcalendar = new GregorianCalendar();
		int month = gcalendar.get(Calendar.MONTH) + 1;
		String sMonth = String.valueOf(month);

		//最終日の取得
		String sResult = setLastData(data, sYear, sMonth);

		data.setYear(sYear);
		data.setMonth(sMonth);
		data.setLastDay(sResult);

		return data;
	}

	public String setLastData(DataBean data, String sYear, String sMonth) {

		int year = Integer.valueOf(sYear);
		int month = Integer.valueOf(sMonth);
		//現在の最終日取得
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);

		int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String sResult = String.valueOf(result);

		return sResult;
	}

	public List<DataBean> display(List<DataBean> dataList, DataBean data) {

		String sYear = data.getYear();
		String sMonth = data.getMonth();
		String lastDay = data.getLastDay();
//		List<kintai> kintailist = searchAll();
//		List<kintai> kintailist = kintaiRepository.findByYmd("2022/04/%");

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
			if(kintailist ==null) {
				
			}else {
				data.setWorkst(kintailist.getWork_st());
				data.setWorked(kintailist.getWork_ed());
				data.setWorkrt(kintailist.getWork_rt());
			}
			
		
			dataList.add(data);
		}

		return dataList;
	}

	public List<kintai> searchAll() {
		// ユーザーTBLの内容を全検索
		return kintaiRepository.findAll();
	}

	public List<kintai> regist() {

		// 入力された値を探す
		//入力された値を登録
		return kintaiRepository.save(null);
	}
}

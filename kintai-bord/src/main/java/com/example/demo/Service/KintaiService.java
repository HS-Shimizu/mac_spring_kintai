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
	public DataBean setData(DataBean data, boolean flag) {

		if(flag) {
			//現在の年取得
			getYear(data);
			//現在の月取得
			getMonth(data);
		}
		
		//最終日の取得
		setLastData(data, data.getYear(), data.getMonth());

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

		String sMonth = data.getMonth();
		String lastDay = data.getLastDay();

		for (int i = 1; i <= Integer.valueOf(lastDay); i++) {

			String year = data.getYear();
			String month = data.getMonth();

			month = padding(month);
			String day = padding(String.valueOf(i));

			String ymd = year + "/" + month + "/" + day;
			kintai kintailist = kintaiRepository.findByYmd(year + month + day);

			data = new DataBean();

			data.setYear(year);
			data.setMonth(sMonth);
			data.setYmd(ymd);
			if (kintailist == null) {

			} else {
				data.setWorkst(kintailist.getWorkSt());
				data.setWorked(kintailist.getWorkEd());
				data.setWorkrt(kintailist.getWorkRt());
			}

			dataList.add(data);
		}

		return dataList;
	}

	/**
	 * データ削除
	 * 
	 */
	public void delete(DataBean data) {

		String year = data.getYear();
		String month = data.getMonth();
		month = padding(month);

		for (int i = 1; i <= Integer.valueOf(data.getLastDay()); i++) {

			
			String day = padding(String.valueOf(i));

			kintaiRepository.deleteById(year + month + day);
			
		}

	}

	/**
	 * データベースに登録
	 * 
	 * @param data
	 * @param workSt
	 * @param workEd
	 * @param workRt
	 */
	public void regist(DataBean data, String workSt, String workEd, String workRt) {

		String year = data.getYear();
		String month = data.getMonth();
		month = padding(month);

		String[] workStList = cut(workSt);
		String[] workEdList = cut(workEd);
		String[] workRtList = cut(workRt);

		for (int i = 1; i <= Integer.valueOf(data.getLastDay()); i++) {
			kintai kintaiData = new kintai();
			
			String day = padding(String.valueOf(i));

			kintaiData.setYmd(year + month + day);
			kintaiData.setWorkSt(workStList[i - 1]);
			kintaiData.setWorkEd(workEdList[i - 1]);
			kintaiData.setWorkRt(workRtList[i - 1]);

			kintaiRepository.save(kintaiData);
		}
	}

	/**
	 * 月と日付を０パディング
	 * 
	 * @param str
	 * @return
	 */
	public String padding(String str) {
		str = String.format("%2s", str).replace(" ", "0");

		return str;
	}
	
	public String[] cut(String str) {
		
		String workTime[] = str.split(",", -1);
		
		return workTime;
		
	}

}

package com.example.demo.controlle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.DataBean;
import com.example.demo.Service.KintaiService;

@Controller
public class KintaiControlle {
	
	 @Autowired
	  KintaiService kintaiService;
	 List<DataBean> dataList = new ArrayList<DataBean>();
	 
	 
	 /*
	  * 初期表示
	  * 
	  */
	@GetMapping("/form")
	private String readForm(Model model) {
		
		DataBean data = new DataBean();
		
		kintaiService.setData(data);
		dataList = kintaiService.display(dataList, data);
		
		model.addAttribute("data", data);
		model.addAttribute("dataList", dataList);

		return "index";
	}

	/*
	 * 2回目以降の表示
	 * 
	 */
	@PostMapping("/form")
	private String Form(@RequestParam("year") String year, @RequestParam("month") String month, DataBean data,
			@RequestParam("work_st") String workst,@RequestParam("work_ed") String worked,@RequestParam("work_rt") String workrt, Model model) {

		dataList = new ArrayList<DataBean>();
		
		data = kintaiService.setData(data , year , month);
		dataList = kintaiService.display(dataList, data);
		String[] work_st = workst.split(",", -1);
		String[] work_ed = worked.split(",", -1);
		String[] work_rt = workrt.split(",", -1);

		model.addAttribute("data", data);
		model.addAttribute("dataList", dataList);

		return "index";
	}
}

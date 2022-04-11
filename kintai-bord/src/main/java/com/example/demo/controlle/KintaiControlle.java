package com.example.demo.controlle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.DataBean;
import com.example.demo.Service.KintaiService;

@Controller
public class KintaiControlle {

	@GetMapping("/form")
	private String readForm(Model model) {

		DataBean data = new DataBean();
		List<DataBean> dataList = new ArrayList<DataBean>();

		KintaiService.setData(data);

		dataList = KintaiService.display(dataList, data);

		model.addAttribute("data", data);
		model.addAttribute("dataList", dataList);

		return "index";
	}

	//	@GetMapping("/form")
	//	private String readForm(Model model) {
	//			
	//		DataBean data = new DataBean();
	//		KintaiService.setData(data);
	//		
	//		model.addAttribute("data", data);
	//		
	//		return "index";
	//	}

	@PostMapping("/form")
	private String Form(@RequestParam("year") String year, @RequestParam("month") String month, DataBean data,
			Model model) {

		data.setYear(year);
		data.setMonth(month);
		data.setLastDay(KintaiService.setLastData(data, year, month));

		List<DataBean> dataList = new ArrayList<DataBean>();

		dataList = KintaiService.display(dataList, data);

		model.addAttribute("data", data);
		model.addAttribute("dataList", dataList);

		return "index";
	}
}

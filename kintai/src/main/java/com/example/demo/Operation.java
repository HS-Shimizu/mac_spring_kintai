package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.User;
import com.example.demo.service.Service;

@Controller
public class Operation {

	//検索が押された時
			@PostMapping("/form")
			private String confirm(@RequestParam("button")String button,@RequestParam("year") String year, @RequestParam("month") String month,User user) {

				user = Service.setData(user,year,month);
				if(button.equals("1") ){
					
				}
				else if(button.equals("2")) {
					//登録
					
				}
				else if(button.equals("3")) {
					
					//削除
				}
				return "index";
			}
}

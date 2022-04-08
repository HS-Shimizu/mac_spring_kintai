package com.example.demo.contoroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Entity.User;
import com.example.demo.Model.DataTransfer;
import com.example.demo.service.Service;

@Controller
public class Contoroller {

	/**
	 * Formオブジェクトを初期化して返却する
	 * @return Formオブジェクト
	 */
	@ModelAttribute
	public User createUser(Model model) {
		User user = new User();

		user = Service.setUser(user);

		return user;
	}

	@ModelAttribute
	public DataTransfer createDataList(Model model) {
		DataTransfer data = new DataTransfer();

		data = Service.setData();

		return data;
	}
	@GetMapping("/form")
	private String readForm(User user , DataTransfer data) {
				
		return "index";
	}

	
	
	
}

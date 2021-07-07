package com.home.coin.change;

import org.springframework.web.bind.annotation.RestController;

import com.service.coin.change.CoinChangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CMController {

	@Autowired
	CoinChangeService serv;

	@RequestMapping("/")
	public String index() {
		System.out.print(serv);
		return "Greetings from Spring Boot!";
	}

}

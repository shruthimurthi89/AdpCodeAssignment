package com.home.coin.change;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.coin.change.CMHandler;

@RestController
public class CMController {

	@Autowired
	CMHandler handler ;

	@RequestMapping("/")
	public String index() {
		return "Greetings!";
	}
	
	@PostMapping("/getChange")
	public String getChange(@RequestBody String amount) {
		return handler.handle(amount);
	}

}

package com.home.coin.change;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.service.coin.change.CMHandler;
import com.service.coin.change.CoinChangeService;

@SpringBootApplication(scanBasePackages = { "com.service.coin.change" })
public class ChangeMachine {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ChangeMachine.class, args);

		handleInputs((CMHandler) ctx.getBean("CMHandler"));

	}

	private static void handleInputs(CMHandler handler) {
		String text ="";
		
		Scanner scan= new Scanner(System.in);
		
		while(true) {
			System.out.println("Please enter one of below options");
			System.out.println("Option 1 : Please enter 1 to get change");		
			System.out.println("Option 2 : Exit");
			
			text = scan.nextLine();
			switch(text) {
			case "1":
				System.out.println("Enter amount you want to get change for");
				text = scan.nextLine();
				System.out.println(handler.handle(text));
				break;
			case "2":
				System.out.println("==================================");
				System.out.println("Exiting");
				System.out.println("==================================");
				System.exit(0);
			}
		}
	}

}

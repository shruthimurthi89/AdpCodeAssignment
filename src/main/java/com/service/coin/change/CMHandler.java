package com.service.coin.change;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CMHandler")
public class CMHandler {

	@Autowired
	CoinChangeService _changeService;
	private Double amount = 0.0;

	public String handle(String amount) {

		if (!validate(amount)) {
			return "Transaction failed.";
		}

		return _changeService.getChange(this.amount).toString();

	}

	private boolean validate(String amount) {

		if (amount.indexOf(".") != -1) {
			String afterDot = amount.substring(amount.indexOf(".") + 1, amount.length());

			if (!(afterDot.equals("0") || afterDot.equals("00"))) {

				System.out.println("Only whole numbers are allowed");
				return false;
			}
		}

		try {
			this.amount = Double.parseDouble(amount);
		} catch (Exception e) {
			System.out.println("Error while parsing input to double" + e.getMessage());
			return false;
		}

		return true;
	}

}

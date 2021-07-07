package com.service.coin.change;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service("CoinChangeService")
public class CoinChangeService {

	private static Map<Double, Integer> coinStore = new HashMap<>();
	private static Map<Double, String> coverter = new HashMap<>();

	static {
		coinStore.put(0.25, 100);
		coinStore.put(0.01, 100);
		coinStore.put(0.05, 100);
		coinStore.put(0.10, 100);

		coverter.put(0.25, "quarters");
		coverter.put(0.01, "pennies");
		coverter.put(0.1, "dime");
		coverter.put(0.05, "nickel");
	}

	public Map<String, Integer> getChange(double amount) {

		double avialable = checkAvailable();
		if (avialable == 0) {
			System.out.println("Machine is out of coins, exiting");
			System.exit(0);
		}

		if (avialable < amount) {
			System.out.println("Machine doesn't have change needed");
			System.out.println("Available change: ");
			System.out.println(coinStore);
			return Collections.<String, Integer>emptyMap();
		}

		List<Double> sortedkeys = coinStore.keySet().stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		Map<String, Integer> paid = new HashMap<>();

		double remaining = amount;

		for (Double dinom : sortedkeys) {

			if (remaining > 0) {
				int needed = (int) (remaining / dinom);

				if (coinStore.get(dinom) < needed) {
					remaining = needed - coinStore.get(dinom);
					paid.put(coverter.get(dinom), coinStore.get(dinom));
					coinStore.put(dinom, 0);
					remaining = remaining * dinom;
				} else {
					paid.put(coverter.get(dinom), needed);
					int diff = coinStore.get(dinom) - needed;
					coinStore.put(dinom, diff);
					remaining = 0;
				}
			}

		}

		return paid;
	}

	private double checkAvailable() {

		double total = 1;

		for (Map.Entry<Double, Integer> entry : coinStore.entrySet()) {
			if (entry.getValue() != 0) {
				total = total + entry.getKey() * entry.getValue();
			}
		}

		return total;

	}

}

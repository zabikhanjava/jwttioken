package com.valtech.jwttoken.util;

import java.util.function.Function;

public class Gen {

	public static void main(String[] args) {

		MyInterfaces<String, Integer> p = x -> Integer.valueOf(x);

		Integer s=p.apply("8");

		Function<String, Integer> d = x -> Integer.valueOf(x);

		d.apply("dsds");

	}
	public <T> String popo(T p) {
		System.out.println("dfsdss"+p);
		Integer i = Integer.valueOf(10);
		return "ss";
	}
}




package com.valtech.jwttoken.util;

import java.util.List;
import java.util.function.Function;

public class Generix {

	public static void main(String[] args) {

		Generix gen=new Generix();
		List<String> sss = gen.pop("8");

		MyInterface p = gen::someCalcuator;
				int a = p.hello(10);
		System.out.println(a);

		List<Integer> ppop = List.of(10,20,30).stream().map(gen::someCalcuator).toList();
		List<Integer> ppopp= List.of(10,20,30).stream().map(x -> x * x).toList();
		Integer ppophp= List.of(10,20,30).stream().reduce(0, (x, y) -> x+y);

		System.out.println(ppophp);

		gen.someCalcuators(x->x*x);


		String role = "PR";
		role = role != null ? role : "GUEST_USER";
		System.out.println(role);

	}

	public <T> List<T> pop(T p) {
		System.out.println("dfsdss"+p);
		return (List<T>) List.of("helllo");
	}

	public int someCalcuator(int a) {
		System.out.println("some"+a);return a*a;
	}

	public int someCalcuators(MyInterface a) {

		return a.hello(10);
	}

}
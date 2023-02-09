package com.valtech.jwttoken.util;

import java.util.ArrayList;
import java.util.List;

public class Jwt {

  public static void main(String[] args) {
    Jwt jwt = new Jwt();
    popop p = jwt::set;
    System.out.println(p.popi());
  }

  public String set() {
    Product product = Product.builder()
        .category("grocery")
        .id("Sre4423")
        .price(32f)
        .build();
    Product product1 = Product.builder()
        .category("sanitery")
        .id("Sre4423")
        .price(43f)
        .build();
    Product product2 = Product.builder()
        .category("constructtion")
        .id("Sre4423")
        .price(54f)
        .build();

    List<Product> listProducts = new ArrayList();
    listProducts.add(product);
    listProducts.add(product1);
    listProducts.add(product2);

    List<Product> s = listProducts.stream().filter(x -> x.getCategory().equals("grocery")).map(x -> x.setDiscount(x)).toList();
	/*	{

			float price=x.getPrice();
			float perce = price/100*15;
			x.setPrice(price+perce);
			return x;
		}).collect(Collectors.toList());
	*/

    popop p = () -> "jk";
    String some = p.popi();

    return some;
  }
}

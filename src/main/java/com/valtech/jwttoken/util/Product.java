package com.valtech.jwttoken.util;

import lombok.Builder;
import lombok.Data;

//@Setter
//@Getter
@Builder
@Data
public class Product {

  String id;
  Float price;
  String category;

  public Product setDiscount(Product p) {

    p.setPrice((p.getPrice() / 100) * 15 + p.getPrice());
    return p;

  }
}
package com.valtech.jwttoken.util;

@FunctionalInterface
interface MyInterfaces<T, R> {
  R apply(T s);
}
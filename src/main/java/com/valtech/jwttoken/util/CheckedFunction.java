package com.valtech.jwttoken.util;

@FunctionalInterface
public interface CheckedFunction<T, R> {

  R apply(T t) throws Exception;
}

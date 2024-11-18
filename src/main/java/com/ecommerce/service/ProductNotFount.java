package com.ecommerce.service;

public class ProductNotFount extends RuntimeException{
    public ProductNotFount(String message){
        super(message);
    }
}

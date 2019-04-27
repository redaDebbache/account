package com.priceminister.account;

public class IllegalBalanceException extends RuntimeException {

    public IllegalBalanceException(Double illegalBalance) {
        super(String.format("Illegal account balance: %f", illegalBalance));
    }
}

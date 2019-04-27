package com.priceminister.account;

public class IllegalAddingNegativeAmountException extends RuntimeException{

    public IllegalAddingNegativeAmountException(double illegalAmount) {
        super(String.format("Illegal negative amount: %1$,.2f", illegalAmount));
    }
}

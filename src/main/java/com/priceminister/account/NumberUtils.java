package com.priceminister.account;


import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;
import static java.util.Objects.requireNonNull;

public class NumberUtils {
    private static final int DEFAULT_SCALE = 2;
    private static final String ILLEGAL_NULL_AMOUNT_DEFAULT_MESSAGE = "Illegal Null Amount";

    public static BigDecimal scale(BigDecimal amount) {
        requireNonNull(amount, ILLEGAL_NULL_AMOUNT_DEFAULT_MESSAGE);
        return amount.setScale(DEFAULT_SCALE, HALF_EVEN);
    }

    public static Double doubleValue(BigDecimal amount) {
        return scale(amount).doubleValue();
    }

    public static Double format(Double amount) {
        requireNonNull(amount, ILLEGAL_NULL_AMOUNT_DEFAULT_MESSAGE);
        return doubleValue(BigDecimal.valueOf(amount));
    }
}

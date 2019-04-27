package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalAddingNegativeAmountException;
import com.priceminister.account.IllegalBalanceException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static com.priceminister.account.NumberUtils.doubleValue;


public class CustomerAccount implements Account {
    private static final String NULL_AMOUNT_DEFAULT_MESSAGE = "Illegal null amount";

    private BigDecimal balance;
    private final AccountRule accountRule;

    public CustomerAccount(AccountRule accountRule) {
        this.accountRule = accountRule;
        this.balance = BigDecimal.ZERO;
    }

    public void add(Double addedAmount) {
        BigDecimal checkedAmount = checkAmountAndGetAsBigDecimal(addedAmount);
        this.balance = balance.add(checkedAmount);
    }

    private BigDecimal checkAmountAndGetAsBigDecimal(Double addedAmount) {
        Objects.requireNonNull(addedAmount, NULL_AMOUNT_DEFAULT_MESSAGE);
        return Optional.of(addedAmount)
                .map(BigDecimal::valueOf)
                .filter(amount -> amount.signum() > 0)
                .orElseThrow(() -> new IllegalAddingNegativeAmountException(addedAmount));
    }

    public Double getBalance() {
        return doubleValue(this.balance);
    }

    /**
     * I'm not comformtable whith the idea of having a possible different accountRule for each Widthraw.
     * As his name says, an AccountRule is inherent to an account.
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount){
        BigDecimal simuatedBalance = balance.add(BigDecimal.valueOf(withdrawnAmount).abs().negate());

        if (accountRule.withdrawPermitted(simuatedBalance)) {
            this.balance = simuatedBalance;
            return getBalance();
        }
        throw new IllegalBalanceException(doubleValue(simuatedBalance));
    }

}

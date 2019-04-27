package com.priceminister.account;

/**
 * This class represents a simple account.
 * It doesn't handle different currencies, all money is supposed to be of standard currency EUR.
 */
public interface Account {
    
    /**
     * Adds money to this account.
     * @param addedAmount - the money to add
     */
    public void add(Double addedAmount);
    
    /**
     * Withdraws money from the account.
     * @param withdrawnAmount - the money to withdraw
     * @return the remaining account balance
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount);
    
    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    public Double getBalance();
}

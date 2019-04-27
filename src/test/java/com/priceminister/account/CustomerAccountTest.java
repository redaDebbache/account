package com.priceminister.account;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * <p>
 * We want to see how you "think code", and how you organize and structure a simple application.
 * <p>
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 */
public class CustomerAccountTest {

    Account customerAccount;

    AccountRule rule;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        rule = new CustomerAccountRule();
        customerAccount = new CustomerAccount(rule);
    }

    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Double balance = customerAccount.getBalance();
        assertThat(balance).isNotNull();
        assertThat(balance).isEqualTo(0.0);
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        //WHEN
       customerAccount.add(10.0);
        //THEN
        assertThat(customerAccount.getBalance()).isEqualTo(10.0);

        //WHEN
       customerAccount.add(20.0);
        //THEN
        assertThat(customerAccount.getBalance()).isEqualTo(30.0);

    }

    @Test
    public void should_not_be_allowed_to_add_negative_amount() {
        //Expected
        expectedException.expect(IllegalAddingNegativeAmountException.class);
        expectedException.expectMessage("Illegal negative amount: -30,54");

        //Given
        double amount = -30.54;
        //When
        customerAccount.add(amount);
    }

    @Test
    public void should_not_be_allowed_to_ad_null_amount() {
        //Given
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Illegal null amount");
        //Given
        Double amount = null;
        //When
        customerAccount.add(amount);

    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void should_withdraw_amount_throw_illegal_balance_exception_for_zero_amount() {

        //EXPECTED
        expectedException.expect(IllegalBalanceException.class);
        expectedException.expectMessage(String.format("Illegal account balance: %f", -20.0));

        //WHEN
        customerAccount.withdrawAndReportBalance(20.0);
    }

    // Also implement missing unit tests for the above functionalities.

    @Test
    public void should_widthraw_amount() {
        //Given
        customerAccount.add(100d);
        //When
        customerAccount.withdrawAndReportBalance(20d);
        //Then
        assertThat(customerAccount.getBalance()).isEqualTo(80d);
    }

    @Test
    public void should_widthdraw_threat_negative_value_as_positive_value() {
        //Given
        customerAccount.add(100d);
        //When
        customerAccount.withdrawAndReportBalance(-20d);
        //Then
        assertThat(customerAccount.getBalance()).isEqualTo(80d);
    }
}

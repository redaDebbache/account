package com.priceminister.account;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsTest {

    @Test
    public void should_scale_throw_NPE_when_amount_is_null() {
        try{
            NumberUtils.scale(null);
        }catch (Exception ex){
            assertThat(ex).isInstanceOf(NullPointerException.class);
            assertThat(ex.getMessage()).isEqualTo("Illegal Null Amount");
        }
    }

    @Test
    public void should_scale_be_always_two_decimals() {
       assertThat(NumberUtils.scale(BigDecimal.ZERO)).isEqualTo("0.00");
       assertThat(NumberUtils.scale(BigDecimal.TEN)).isEqualTo("10.00");
       assertThat(NumberUtils.scale(BigDecimal.valueOf(33.45678))).isEqualTo("33.46");
       assertThat(NumberUtils.scale(BigDecimal.valueOf(-33.4))).isEqualTo("-33.40");
    }

    @Test
    public void should_doubleValue_throw_NPE_when_amount_is_null() {
        try{
            NumberUtils.doubleValue(null);
        }catch (Exception ex){
            assertThat(ex).isInstanceOf(NullPointerException.class);
            assertThat(ex.getMessage()).isEqualTo("Illegal Null Amount");
        }
    }

    @Test
    public void should_convert_bigDecimal_to_a_formatted_double() {
        assertThat(NumberUtils.doubleValue(BigDecimal.ZERO)).isEqualTo(0.00);
        assertThat(NumberUtils.doubleValue(BigDecimal.TEN)).isEqualTo(10.00);
        assertThat(NumberUtils.doubleValue(BigDecimal.valueOf(33.45678))).isEqualTo(33.46);
        assertThat(NumberUtils.doubleValue(BigDecimal.valueOf(-33.4))).isEqualTo(-33.40);
    }

    @Test
    public void should_format_a_given_double() {
        assertThat(NumberUtils.format(0d)).isEqualTo(0.00);
        assertThat(NumberUtils.format(10.0)).isEqualTo(10.00);
        assertThat(NumberUtils.format(33.45678)).isEqualTo(33.46);
        assertThat(NumberUtils.format(-33.4)).isEqualTo(-33.40);

    }
}
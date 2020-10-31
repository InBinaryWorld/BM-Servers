package dev.szafraniak.bmresource.utils;

import dev.szafraniak.bmresource.model.action.invoice.AmountModel;
import dev.szafraniak.bmresource.model.action.invoice.PriceModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

public final class FinancialUtils {

    public static <T> BigDecimal sumBy(List<T> list, Function<T, BigDecimal> fieldFunc) {
        return list.stream().reduce(new BigDecimal("0"), (acc, next) -> acc.add(fieldFunc.apply(next)), BigDecimal::add);
    }

    public static AmountModel countAmount(PriceModel price, BigDecimal quantity) {
        return countAmount(price.getNet(), price.getTaxRate(), quantity);
    }

    public static AmountModel countAmount(BigDecimal netPrice, BigDecimal taxRate, BigDecimal quantity) {
        BigDecimal net = netPrice.setScale(2, RoundingMode.HALF_UP)
                .multiply(quantity).setScale(2, RoundingMode.HALF_UP);
        return countAmount(net, taxRate);
    }

    public static AmountModel countAmount(BigDecimal netAmount, BigDecimal taxRate) {
        BigDecimal tax = taxRate.movePointLeft(2).multiply(netAmount)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal gross = netAmount.add(tax);
        return new AmountModel(netAmount, tax, gross);
    }

    public static AmountModel countTaxGroupAmount(BigDecimal taxRate, List<BigDecimal> netAmounts) {
        BigDecimal netAmount = sumBy(netAmounts, net -> net);
        return countAmount(netAmount, taxRate);
    }

}

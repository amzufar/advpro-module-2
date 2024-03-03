package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class VoucherCode extends PaymentMethod {

    public VoucherCode(Map<String, String> paymentData) {
        super(paymentData);
    }

    @Override
    boolean isPaymentDataSizeValid(Map<String, String> paymentData) {
        return paymentData.size() == 1;
    }

    @Override
    boolean isPaymentDataKeyValid(Map<String, String> paymentData) {
        return paymentData.containsKey("voucherCode");
    }

    @Override
    public boolean isValid() {
        return this.isVoucherLengthValid() &&
                this.isVoucherStartsWithEshop() &&
                this.isVoucherNumCharAmountValid();
    }

    boolean isVoucherLengthValid() {
        return this.paymentData.get("voucherCode").length() == 16;
    }

    boolean isVoucherStartsWithEshop() {
        return this.paymentData.get("voucherCode").startsWith("ESHOP");
    }

    boolean isVoucherNumCharAmountValid() {
        return this.paymentData.get("voucherCode")
                .chars()
                .filter(Character::isDigit)
                .count() == 8;
    }
}
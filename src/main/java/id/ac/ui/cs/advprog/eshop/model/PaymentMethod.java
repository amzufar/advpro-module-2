package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public abstract class PaymentMethod {
    Map<String, String> paymentData;

    PaymentMethod(Map<String, String> paymentData) {
        if (this.isPaymentDataSizeValid(paymentData) && this.isPaymentDataKeyValid(paymentData)) {
            this.paymentData = paymentData;
        } else {
            throw new IllegalArgumentException();
        }
    }

    abstract boolean isPaymentDataSizeValid(Map<String, String> paymentData);
    abstract boolean isPaymentDataKeyValid(Map<String, String> paymentData);
    public abstract boolean isValid();
}

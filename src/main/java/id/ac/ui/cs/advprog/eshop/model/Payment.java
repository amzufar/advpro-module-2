package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private PaymentMethod paymentMethod;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.paymentMethod = checkPaymentMethod(method, paymentData);
        this.id = id;
        this.status = checkStatus(this.paymentMethod);
        this.paymentData = paymentData;
        this.method = method;
    }

    private PaymentMethod checkPaymentMethod(String method, Map<String, String> paymentData) {
        if (method.equals("cashOnDelivery")) {
            return new CashOnDelivery(paymentData);
        } else if (method.equals("voucherCode")) {
            return new VoucherCode(paymentData);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String checkStatus(PaymentMethod paymentMethod) {
        if (paymentMethod.isValid()) {
            return "SUCCESS";
        } else {
            return "REJECTED";
        }
    }
}

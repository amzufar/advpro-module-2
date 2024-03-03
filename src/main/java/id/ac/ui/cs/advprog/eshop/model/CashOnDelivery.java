package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class CashOnDelivery extends PaymentMethod {

    public CashOnDelivery(Map<String, String> paymentData) {
        super(paymentData);
    }

    @Override
    boolean isPaymentDataSizeValid(Map<String, String> paymentData) {
        return paymentData.size() == 2;
    }

    @Override
    boolean isPaymentDataKeyValid(Map<String, String> paymentData) {
        return paymentData.containsKey("address") && paymentData.containsKey("deliveryFee");
    }

    @Override
    public boolean isValid() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        return address != null &&
                deliveryFee != null &&
                !address.isEmpty() &&
                !deliveryFee.isEmpty();
    }
}

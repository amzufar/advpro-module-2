package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> paymentData = (Map<String, String>) new HashMap<>().put("voucherCode", "ESHOP1234ABC5678");

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("123", "voucherCode", "SUCCESS", paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
        assertEquals("123", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentUsingInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "credit", "SUCCESS", paymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("123", "voucherCode", "SUCCESS", paymentData);
        payment.setStatus("CANCELLED");
        assertEquals("CANCELLED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "voucherCode", "MEOW", paymentData);
        });
    }
}

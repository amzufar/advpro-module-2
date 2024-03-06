package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreatePaymentMethodCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "depok");
        paymentData.put("deliveryFee", "12000");
        Payment payment = new Payment("123", "cashOnDelivery", paymentData);
        assertNotNull(payment);
        assertEquals("123", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentMethodVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("123", "voucherCode", paymentData);
        assertNotNull(payment);
        assertEquals("123", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentUsingInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "credit", paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherUsingEmptyMap() {
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "voucherCode", paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherUsingInvalidKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("meow", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "voucherCode", paymentData);
        });
    }

    @Test
    void testCreatePaymentUsingInvalidVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESSOP1234ABC5678");
        Payment payment = new Payment("123", "voucherCode", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashOnDeliveryUsingEmptyMap() {
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "cashOnDelivery", paymentData);
        });
    }

    @Test
    void testCreatePaymentCashOnDeliveryUsingInvalidKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("meow", "depok");
        paymentData.put("addressFee", "12000");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "voucherCode", paymentData);
        });
    }

    @Test
    void testCreatePaymentCashOnDeliveryUsingInvalidAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("addressFee", "12000");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123", "voucherCode", paymentData);
        });
    }
}
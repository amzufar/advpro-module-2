package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    List<Payment> payments;

    Map<String, String> paymentData = (Map<String, String>) new HashMap<>().put("voucherCode", "ESHOP1234ABC5678");

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        Payment payment1 = new Payment("123", "voucherCode", "SUCCESS", paymentData);
        payments.add(payment1);
        Payment payment2 = new Payment("124", "cashOnDelivery", "REJECTED", paymentData);
        payments.add(payment2);
        Payment payment3 = new Payment("125", "voucherCode", "SUCCESS", paymentData);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {

    }
}

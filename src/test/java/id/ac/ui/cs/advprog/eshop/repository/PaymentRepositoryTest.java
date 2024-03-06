package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71f6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testAddPaymentUsingVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Order order = orders.get(1);

        Payment result = paymentRepository.addPayment(order, "voucherCode", paymentData);
        assertNotNull(result);
        assertEquals("voucherCode", result.getMethod());
    }

    @Test
    void testAddPaymentUsingCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "depok");
        paymentData.put("deliveryFee", "12000");
        Order order = orders.get(1);

        Payment result = paymentRepository.addPayment(order, "cashOnDelivery", paymentData);
        assertNotNull(result);
        assertEquals("cashOnDelivery", result.getMethod());
    }

    @Test
    void testGetPaymentIfIdFound() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Order order = orders.get(1);

        Payment payment = paymentRepository.addPayment(order, "voucherCode", paymentData);
        Payment result = paymentRepository.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Order order = orders.get(1);

        Payment payment = paymentRepository.addPayment(order, "voucherCode", paymentData);
        Payment result = paymentRepository.getPayment("zczc");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        Map<String, String> voucherCode = new HashMap<>();
        voucherCode.put("voucherCode", "ESHOP1234ABC5678");
        Map<String, String> cashOnDelivery = new HashMap<>();
        cashOnDelivery.put("address", "depok");
        cashOnDelivery.put("deliveryFee", "12000");
        Order order1 = orders.get(0);
        Order order2 = orders.get(1);

        paymentRepository.addPayment(order1, "voucherCode", voucherCode);
        paymentRepository.addPayment(order2, "cashOnDelivery", cashOnDelivery);

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(2, allPayments.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(0, allPayments.size());
    }
}
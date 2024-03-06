package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Product> products;
    List<Order> orders;

    List<Payment> payments;

    List<Map<String, String>> paymentDatas;

    @BeforeEach
    void setUp() {
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

        paymentDatas = new ArrayList<>();
        Map<String, String> voucherCode = new HashMap<>();
        voucherCode.put("voucherCode", "ESHOP1234ABC5678");
        paymentDatas.add(voucherCode);

        Map<String, String> cashOnDelivery = new HashMap<>();
        cashOnDelivery.put("address", "depok");
        cashOnDelivery.put("deliveryFee", "12000");
        paymentDatas.add(cashOnDelivery);

        payments = new ArrayList<>();
        Payment payment1 = new Payment("123", "voucherCode", voucherCode);
        payments.add(payment1);
        Payment payment2 = new Payment("124", "cashOnDelivery", cashOnDelivery);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(payment).when(paymentRepository).addPayment(order, payment.getMethod(), payment.getPaymentData());

        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        verify(paymentRepository, times(1)).addPayment(eq(order), eq(payment.getMethod()), eq(payment.getPaymentData()));
    }

    @Test
    void testAddPaymentInvalidMethod() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, "meow", payment.getPaymentData());
        });
        verify(paymentRepository, times(0)).addPayment(eq(order), eq("meow"), eq(payment.getPaymentData()));
    }

    @Test
    void testAddPaymentEmptyVoucher() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        Map<String, String> invalidVoucher = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, payment.getMethod(), invalidVoucher);
        });
        verify(paymentRepository, times(0)).addPayment(eq(order), eq(payment.getMethod()), eq(invalidVoucher));
    }

    @Test
    void testAddPaymentInvalidVoucherKey() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        Map<String, String> invalidVoucher = new HashMap<>();
        invalidVoucher.put("meow", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, payment.getMethod(), invalidVoucher);
        });
        verify(paymentRepository, times(0)).addPayment(eq(order), eq(payment.getMethod()), eq(invalidVoucher));
    }

    @Test
    void testAddPaymentInvalidCashOnDeliveryKey() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        Map<String, String> invalidCashOnDelivery = new HashMap<>();
        invalidCashOnDelivery.put("meow", "depok");
        invalidCashOnDelivery.put("addressFee", "12000");
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, payment.getMethod(), invalidCashOnDelivery);
        });
        verify(paymentRepository, times(0)).addPayment(eq(order), eq(payment.getMethod()), eq(invalidCashOnDelivery));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        verify(paymentRepository, times(1)).getPayment(payment.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).getPayment("zczc");

        Payment result = paymentService.getPayment("zczc");
        assertNull(result);
        verify(paymentRepository, times(1)).getPayment("zczc");
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments.size(), result.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
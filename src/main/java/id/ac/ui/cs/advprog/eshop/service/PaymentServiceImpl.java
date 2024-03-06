package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(generateId(), method, paymentData);
        this.paymentRepository.addPayment(order, payment.getMethod(), payment.getPaymentData());
        return payment;
    }

    @Override
    public String setStatus(Payment payment, String status) {
        return paymentRepository.setStatus(payment, status);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}
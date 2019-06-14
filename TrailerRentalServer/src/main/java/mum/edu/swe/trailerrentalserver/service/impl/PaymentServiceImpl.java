package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Payment;
import mum.edu.swe.trailerrentalserver.repository.PaymentRepository;
import mum.edu.swe.trailerrentalserver.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public boolean delete(Long id) {
        paymentRepository.deleteById(id);
        return true;
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Payment::getPaymentId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> findAllByRentId(Long rentId) {
        return paymentRepository.findAllByRentId(rentId)
                .stream()
                .sorted(Comparator.comparing(Payment::getPaymentId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findAllByUserId(Long userId) {
        return paymentRepository.findAllByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(Payment::getPaymentId).reversed())
                .collect(Collectors.toList());
    }
}

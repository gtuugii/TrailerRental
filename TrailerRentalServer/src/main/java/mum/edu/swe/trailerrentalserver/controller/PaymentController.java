package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Payment;
import mum.edu.swe.trailerrentalserver.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getAll(){
        return (List<Payment>) paymentService.findAll();
    }

    @GetMapping("/payment/{id}")
    public Payment getOne(@PathVariable("id") Long id){
        return paymentService.findById(id);
    }

    @PostMapping("/payment")
    public Payment save(@RequestBody Payment payment){
        return paymentService.save(payment);
    }

    @DeleteMapping("/payment/{id}")
    public Payment delete(@PathVariable("id") Long id){
        Payment payment = paymentService.findById(id);
        paymentService.delete(id);
        return payment;
    }

}

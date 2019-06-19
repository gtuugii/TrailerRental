package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Dashboard;
import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.service.PaymentService;
import mum.edu.swe.trailerrentalserver.service.RentService;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import mum.edu.swe.trailerrentalserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired
    TrailerService trailerService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    RentService rentService;

    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public Dashboard dashboard(){
        Dashboard dashboard = new Dashboard();
        System.out.println("dashboard =====");

        dashboard.setAvailTrailers(trailerService.countAllByStatus(1).longValue());
        dashboard.setTotalTrailers(trailerService.findAll().stream().collect(Collectors.counting()));
        dashboard.setUnderTrailers(trailerService.countAllByStatus(0).longValue());
        dashboard.setTotalPayments(paymentService.findAll().stream().collect(Collectors.counting()));
        dashboard.setTotalRents(rentService.findAll().stream().collect(Collectors.counting()));
        dashboard.setTotalTenants(userService.findAll().stream().collect(Collectors.counting()));

        return dashboard;
    }

}

package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.config.TokenHelper;
import mum.edu.swe.trailerrentalclient.model.DB;
import mum.edu.swe.trailerrentalclient.model.Payment;
import mum.edu.swe.trailerrentalclient.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    private String api_url = Config.URL;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    DB status;

    @GetMapping("/paymentReport")
    public String paymentReport(){
        return "reports/payment-report";
    }

    @GetMapping("/paymentReport/search")
    public String availableList(@RequestParam("trailernumber") String trailernumber,
                                Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Payment[]> entity = new HttpEntity<Payment[]>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Payment[]> response = restTemplate.exchange(
                    api_url + "payments/search?trailernumber="+trailernumber,
                    HttpMethod.GET, entity, Payment[].class);
            final List<Payment> payments = Arrays.stream(response.getBody()).collect(Collectors.toList());

            System.out.println("payments: " + payments);
            model.addAttribute("payments", payments);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("search - reports/payment-report");
        return "reports/payment-report";
    }

    @GetMapping("/rentReport")
    public String rentReport(){
        return "reports/rent-report";
    }

    @GetMapping("/rentReport/search")
    public String search(@RequestParam("trailernumber") String trailernumber,
                         @RequestParam("statusID") Integer statusID,
                         Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Rent[]> entity = new HttpEntity<Rent[]>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Rent[]> response = restTemplate.exchange(
                    api_url + "rents/search?trailernumber="+trailernumber+"&statusID="+statusID,
                    HttpMethod.GET, entity, Rent[].class);
            final List<Rent> rents = Arrays.stream(response.getBody()).collect(Collectors.toList());

            System.out.println("rents: " + rents);

            model.addAttribute("status", status.rentStatus);
            model.addAttribute("rents", rents);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("search - rent-report");
        return "reports/rent-report";
    }

}

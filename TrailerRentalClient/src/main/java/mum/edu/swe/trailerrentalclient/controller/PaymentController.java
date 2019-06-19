package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.config.TokenHelper;
import mum.edu.swe.trailerrentalclient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private String api_url = Config.URL;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    DB status;

    @GetMapping("/list")
    public String list(Model model){
        try{
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Payment[]> entity = new HttpEntity<Payment[]>(headers);

            ResponseEntity<Payment[]> response = restTemplate.exchange(api_url + "payments", HttpMethod.GET, entity, Payment[].class);
            final List<Payment> payments = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("payments", payments);
            System.out.println("list - payments: " + payments);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "payments/payment-list";
    }

    @GetMapping("/search")
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
        System.out.println("search - payments");
        return "payments/payment-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Payment> response = restTemplate.exchange(api_url + "payment/" + id.toString(), HttpMethod.GET, entity, Payment.class);

            Payment r = response.getBody();
            System.out.println("response: " + r);

            model.addAttribute("payment", r);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "payments/payment-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Payment payment, Model model, HttpSession session){
        System.out.println("payments/payment-add");
        payment.setPayDate(LocalDate.now());
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity<Rent[]> entity = new HttpEntity<Rent[]>(headers);
        //rents/search?trailernumber="+trailernumber+"&statusID="+statusID
        ResponseEntity<Rent[]> response = restTemplate.exchange(api_url + "rents/search?trailernumber=&statusID=2", HttpMethod.GET, entity, Rent[].class);
        final List<Rent> rents = Arrays.stream(response.getBody()).collect(Collectors.toList());

        session.setAttribute("rentslist", rents);
        model.addAttribute("rentslist", rents);

        return "payments/payment-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Payment payment,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       HttpSession session,
                       Model model){
        String result_str = "saved";
        try {
            model.addAttribute("rentslist", session.getAttribute("rentslist"));

            if (bindingResult.hasErrors()) {
                return "payments/payment-add";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url + "payment/", entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "payments/payment-add";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("payment", payment);
            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "payments/payment-add";
        }
        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session){
        try {

            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            //get rents
            HttpEntity<Rent[]> entity = new HttpEntity<Rent[]>(headers);
            ResponseEntity<Rent[]> response = restTemplate.exchange(api_url + "rents", HttpMethod.GET, entity, Rent[].class);
            final List<Rent> rents = Arrays.stream(response.getBody()).collect(Collectors.toList());

            session.setAttribute("rentslist", rents);
            model.addAttribute("rentslist", rents);

            //get payment
            HttpEntity entity3 = new HttpEntity<>(headers);
            ResponseEntity<Payment> response3 = restTemplate.exchange(api_url + "payment/" + id.toString(), HttpMethod.GET, entity3, Payment.class);

            Payment payment = response3.getBody();
            System.out.println("response3: " + payment);

            model.addAttribute("payment", payment);

        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "payments/payment-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Payment payment,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       HttpSession session,
                       Model model){
        try {
            model.addAttribute("rentslist", session.getAttribute("rentslist"));

            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "payments/payment-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.exchange(api_url + "payment/", HttpMethod.POST, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "payments/payment-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "payments/payment-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes,
                         Model model){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "payment/" + id, HttpMethod.DELETE, entity, String.class);
            System.out.println("result: " + result.getBody());

            String result_str = "Deleted";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            model.addAttribute("resultInfo", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("resultInfo", e);
        }
        return "result";
    }
}

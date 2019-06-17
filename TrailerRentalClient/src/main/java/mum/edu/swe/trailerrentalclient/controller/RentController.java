package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.model.DB;
import mum.edu.swe.trailerrentalclient.model.Rent;
import mum.edu.swe.trailerrentalclient.model.Trailer;
import mum.edu.swe.trailerrentalclient.model.User;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rents")
@SessionAttributes({ "trailerslist", "userslist" })
public class RentController {

    private String api_url = Config.URL;

    @Autowired
    DB status;

    @GetMapping("/list")
    public String list(Model model){
        try{
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Rent[]> entity = new HttpEntity<Rent[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Rent[]> response = restTemplate.exchange(api_url + "rents", HttpMethod.GET, entity, Rent[].class);
            final List<Rent> rents = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("rents", rents);
            System.out.println("list - rents: " + rents);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "rents/rent-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Rent> response = restTemplate.exchange(api_url + "rent/" + id.toString(), HttpMethod.GET, entity, Rent.class);

            Rent r = response.getBody();
            System.out.println("response: " + r);

            model.addAttribute("rent", r);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "rents/rent-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Rent rent, Model model, HttpSession session){
        System.out.println("rents/rent-add");

        model.addAttribute("status", status.rentStatus);
        System.out.println(status.rentStatus);

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Trailer[]> entity = new HttpEntity<Trailer[]>(headers);
        ResponseEntity<Trailer[]> response = restTemplate.exchange(api_url + "trailers", HttpMethod.GET, entity, Trailer[].class);
        final List<Trailer> trailers = Arrays.stream(response.getBody()).collect(Collectors.toList());

        session.setAttribute("trailerslist", trailers);
        model.addAttribute("trailerslist", trailers);

        HttpEntity<User[]> entity2 = new HttpEntity<User[]>(headers);
        ResponseEntity<User[]> response2 = restTemplate.exchange(api_url + "users", HttpMethod.GET, entity2, User[].class);
        final List<User> users = Arrays.stream(response2.getBody()).collect(Collectors.toList());

        session.setAttribute("userslist", users);
        model.addAttribute("userslist", users);

        return "rents/rent-edit";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Rent rent,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       HttpSession session,
                       Model model){
        String result_str = "saved";
        try {
            model.addAttribute("status", status.rentStatus);
            model.addAttribute("trailerslist", session.getAttribute("trailerslist"));
            model.addAttribute("userslist", session.getAttribute("userslist"));

            if (bindingResult.hasErrors()) {
                return "rents/rent-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Rent> entity = new HttpEntity<>(rent, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url + "rent/", entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "rents/rent-edit";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("rent", rent);
            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "rents/rent-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        try {

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Rent> response = restTemplate.exchange(api_url + "rent/" + id.toString(), HttpMethod.GET, entity, Rent.class);

            Rent rent = response.getBody();
            System.out.println("response: " + rent);

            model.addAttribute("status", status.rentStatus);
            model.addAttribute("rent", rent);

        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "rents/rent-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Rent rent,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        try {
            model.addAttribute("status", status.rentStatus);

            if (bindingResult.hasErrors()) {
                return "rents/rent-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Rent> entity = new HttpEntity<>(rent, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.exchange(api_url + "rent/", HttpMethod.POST, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "rents/rent-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "rents/rent-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes,
                         Model model){

        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "rent/" + id, HttpMethod.DELETE, entity, String.class);
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

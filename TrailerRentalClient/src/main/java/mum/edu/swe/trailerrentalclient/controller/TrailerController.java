package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.model.DB;
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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trailers")
public class TrailerController {

    private String api_url = Config.URL;

    @Autowired
    DB status;


    @GetMapping("/list")
    public String list(Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Trailer[]> entity = new HttpEntity<Trailer[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Trailer[]> response = restTemplate.exchange(api_url + "trailers", HttpMethod.GET, entity, Trailer[].class);
            final List<Trailer> trailers = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("trailers", trailers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("list - trailers");
        return "trailers/trailer-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Trailer> response = restTemplate.exchange(api_url + "trailer/" + id.toString(), HttpMethod.GET, entity, Trailer.class);

            Trailer trailer = response.getBody();
            System.out.println("response: " + trailer);

            model.addAttribute("trailer", trailer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "trailers/trailer-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Trailer trailer, Model model) {
        System.out.println("trailers/user-add");

        model.addAttribute("status", status.trailerStatus);
        model.addAttribute("trailerType", status.trailerType);

        return "trailers/trailer-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Trailer trailer,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        String result_str = "saved";
        try {
            model.addAttribute("status", status.trailerStatus);
            model.addAttribute("trailerType", status.trailerType);

            if (bindingResult.hasErrors()) {
                return "trailers/trailer-add";
            }

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Trailer> entity = new HttpEntity<>(trailer, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url + "trailer/", entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "trailers/trailer-add";
            }

            if (!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("trailer", trailer);
            redirectAttributes.addFlashAttribute("result", result_str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "trailers/trailer-add";
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        try {

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Trailer> response = restTemplate.exchange(api_url + "trailer/" + id.toString(), HttpMethod.GET, entity, Trailer.class);

            Trailer trailer = response.getBody();
            System.out.println("response: " + trailer);

            model.addAttribute("status", status.trailerStatus);
            model.addAttribute("trailerType", status.trailerType);
            model.addAttribute("trailer", trailer);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return "trailers/trailer-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Trailer trailer,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        try {
            model.addAttribute("status", status.trailerStatus);
            model.addAttribute("trailerType", status.trailerType);

            if (bindingResult.hasErrors()) {
                return "trailers/trailer-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Trailer> entity = new HttpEntity<>(trailer, headers);

            RestTemplate restTemplate = new RestTemplate();

            //ResponseEntity<String> result = restTemplate.exchange(api_url + "user/", HttpMethod.PUT, entity, String.class);
            ResponseEntity<String> result = restTemplate.exchange(api_url + "trailer/", HttpMethod.POST, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "trailers/trailer-edit";
            }

            String result_str = "edited";
            if (!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "trailers/trailer-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "trailer/" + id, HttpMethod.DELETE, entity, String.class);
            System.out.println("result: " + result.getBody());

            String result_str = "Deleted";
            if (!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            model.addAttribute("resultInfo", result_str);
            redirectAttributes.addFlashAttribute("result", result_str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("resultInfo", e);
        }
        return "redirect:list";
    }

}

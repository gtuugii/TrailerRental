package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.config.TokenHelper;
import mum.edu.swe.trailerrentalclient.model.DB;
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
@RequestMapping("/users")
public class UserController {

    private String api_url = Config.URL;
    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    DB status;

    //@Autowired
    //RestTemplate restTemplate;

    @GetMapping("/list")
    public String list(Model model){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<User[]> response = restTemplate.exchange(api_url + "users", HttpMethod.GET, entity, User[].class);
            final List<User> users = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("users", users);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("list - users");
        return "users/user-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<User> response = restTemplate.exchange(api_url + "user/" + id.toString(), HttpMethod.GET, entity, User.class);

            User user = response.getBody();
            System.out.println("response: " + user);

            model.addAttribute("user", user);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "users/user-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute User user, Model model){
        System.out.println("users/user-add");

        model.addAttribute("status", status.userStatus);
        model.addAttribute("sex", status.sex);
        System.out.println(status.userStatus);

        return "users/user-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute User user,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        String result_str = "saved";
        try {
            model.addAttribute("status", status.userStatus);
            model.addAttribute("sex", status.sex);

            if (bindingResult.hasErrors()) {
                return "users/user-add";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<User> entity = new HttpEntity<>(user, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url + "user/", entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "users/user-add";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "users/user-add";
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model){
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<User> response = restTemplate.exchange(api_url + "user/" + id.toString(), HttpMethod.GET, entity, User.class);

            User user = response.getBody();
            System.out.println("response: " + user);

            model.addAttribute("status", status.userStatus);
            model.addAttribute("sex", status.sex);
            model.addAttribute("user", user);

        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "users/user-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute User user,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        try {
            model.addAttribute("status", status.userStatus);
            model.addAttribute("sex", status.sex);

            if (bindingResult.hasErrors()) {
                return "users/user-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();

            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<User> entity = new HttpEntity<>(user, headers);

            ResponseEntity<String> result = restTemplate.exchange(api_url + "user/", HttpMethod.POST, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "users/user-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "users/user-edit";
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
            ResponseEntity<String> result = restTemplate.exchange(api_url + "user/" + id, HttpMethod.DELETE, entity, String.class);
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

package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.config.TokenHelper;
import mum.edu.swe.trailerrentalclient.config.ConfAuthenticationModel;
import mum.edu.swe.trailerrentalclient.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private TokenHelper tokenHelper;

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute Login login) {
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(@ModelAttribute Login login) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Config.URL_AUTH + "?token=123");
        tokenHelper.deleteToken();

        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute Login login, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(Config.URL_AUTH, login, String.class);
        System.out.println(result.getBody());
        if (result.getBody() != null && !result.getBody().trim().isEmpty()) {
            session.setAttribute("token", result.getBody());

            ConfAuthenticationModel confAuthenticationModel = new ConfAuthenticationModel();
            confAuthenticationModel.setAuthenticated(true);
            confAuthenticationModel.setToken(result.getBody());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity(headers);

            ResponseEntity<List<String>> authResponse = restTemplate.exchange(Config.URL_AUTH,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<String>>() {
                    });

            if (authResponse!=null){
                session.setAttribute("roles", authResponse.getBody());

                System.out.println("roles: " + authResponse.getBody());

                String csvRoles = String.join(",", authResponse.getBody());
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(csvRoles);
                confAuthenticationModel.setAuthorities(grantedAuthorities);
            }

            SecurityContextHolder.getContext().setAuthentication(confAuthenticationModel);

            //return "index";
            return "redirect:/home";
        }
        return "redirect:/auth/login?error";
    }

}

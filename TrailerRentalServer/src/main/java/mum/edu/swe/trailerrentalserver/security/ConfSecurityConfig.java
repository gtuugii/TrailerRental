package mum.edu.swe.trailerrentalserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    protected AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
        l.add(jwtAuthenticationProvider);
        return new ProviderManager(l);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() throws Exception {

        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        filter.setAuthenticationFailureHandler(new JwtFailureHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/api/authentication*").permitAll()
                .antMatchers("/images*").permitAll()
                .antMatchers("/api").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterAfter(corsFilter,UsernamePasswordAuthenticationFilter.class);
        //http.headers().cacheControl();
        http.headers().frameOptions().sameOrigin();

        // pass CORS when ajax calls
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // it works but DELETE method does not work
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()
//        );

        http.cors().configurationSource(request -> config);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}

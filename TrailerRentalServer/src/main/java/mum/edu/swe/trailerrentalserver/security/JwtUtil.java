package mum.edu.swe.trailerrentalserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtil {

    private static final String secret = "topSecret";

    public String encode(JwtUserDetails jwtUserDetails) {
        Claims claims = Jwts.claims().setSubject(jwtUserDetails.getUsername());
        claims.put("username", jwtUserDetails.getUsername());
        claims.put("firstName", jwtUserDetails.getFirstName());
        claims.put("lastName", jwtUserDetails.getLastName());
        claims.put("roles", jwtUserDetails.getRoles());
        claims.put("id", jwtUserDetails.getId());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public JwtUserDetails decode(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            JwtUserDetails jwtUserDetails = new JwtUserDetails();
            jwtUserDetails.setUsername((String) body.get("username"));
            jwtUserDetails.setFirstName((String) body.get("firstName"));
            jwtUserDetails.setLastName((String) body.get("lastName"));
            jwtUserDetails.setRoles((List<String>) body.get("roles"));
            jwtUserDetails.setId(Long.parseLong(body.get("id").toString()));

            return jwtUserDetails;
        } catch (Exception e) {

        }
        return null;
    }

}

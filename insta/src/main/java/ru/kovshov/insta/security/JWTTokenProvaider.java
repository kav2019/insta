package ru.kovshov.insta.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.kovshov.insta.model.User;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvaider {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvaider.class);

    public String generatedToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);

        String userId = Long.toString(user.getId());
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("username", user.getEmail());
        claimsMap.put("firstname", user.getName());
        claimsMap.put("lastname", user.getLastname());


//        Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKey));


        return Jwts.builder()
                .setSubject(userId)
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.SECRET.getBytes()), SignatureAlgorithm.HS512)
                .compact();
//                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
//                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
                    .setSigningKey(SecurityConstants.SECRET)
                    .build()
                    .parse(token);
            return true;
        }catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token){
        Jws<Claims> claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
                .setSigningKey(SecurityConstants.SECRET)
                .build()
                .parseClaimsJws(token);
        String id = (String) claims.getBody().get("id");

//        Claims claims = Jwts.parser()
//                .setSigningKey(SecurityConstants.SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//        String idd = (String) claims.get("id");
        return Long.parseLong(id);
    }
}

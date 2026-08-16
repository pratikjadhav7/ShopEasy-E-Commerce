package ShopEasy.serviceImpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import ShopEasy.model.User;
import ShopEasy.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateToken(User user) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ShopEasy")
                .issuedAt(now)
                .expiresAt(
                        now.plus(24, ChronoUnit.HOURS)
                )
                .subject(
                        String.valueOf(user.getUserId())
                )
                .claim(
                        "name",
                        user.getName()
                )
                .claim(
                        "phone",
                        user.getPhone()
                )
                .claim(
                        "role",
                        user.getRole().name()
                )
                .build();

        return jwtEncoder
                .encode(
                        JwtEncoderParameters.from(claims)
                )
                .getTokenValue();
    }
}
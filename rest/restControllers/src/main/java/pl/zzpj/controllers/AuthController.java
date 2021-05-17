package pl.zzpj.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.controller.SignInUseCase;
import pl.zzpj.dto.UserCredentialsDto;
import pl.zzpj.rest.mappers.UserCredentialsMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignInUseCase signInUseCase;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(SignInUseCase signInUseCase, AuthenticationManager authenticationManager) {
        this.signInUseCase = signInUseCase;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> signIn(@RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            userCredentialsDto.setPassword(DigestUtils.sha512Hex(userCredentialsDto.getPassword()));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentialsDto.getLogin(), userCredentialsDto.getPassword())
            );
        } catch (BadCredentialsException | LockedException | DisabledException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDetails userDetails = signInUseCase.signIn(UserCredentialsMapper.mapToUserCredentials(userCredentialsDto).getLogin());

        return ResponseEntity.ok(generateToken(userDetails));
    }

    private String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuer(SecurityConstants.ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + SecurityConstants.JWT_TIMEOUT))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .compact();
    }
}

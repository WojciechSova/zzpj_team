package pl.zzpj.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.controller.SignInUseCase;
import pl.zzpj.modelDto.UserCredentialsDto;
import pl.zzpj.rest.mappers.UserCredentialsMapper;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignInUseCase signInUseCase;
//    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
//        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void signIn(UserCredentialsDto userCredentialsDto) {
        try {
            userCredentialsDto.setPassword(DigestUtils.sha512Hex(userCredentialsDto.getPassword()));
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userCredentialsDto.getLogin(), userCredentialsDto.getPassword())
//            );
        } catch (BadCredentialsException | LockedException | DisabledException ex) {
            return;
        }
        signInUseCase.signIn(UserCredentialsMapper.mapToUserCredentials(userCredentialsDto));
    }
}

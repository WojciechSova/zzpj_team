package pl.zzpj.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.zzpj.model.UserCredentials;

public interface SignInUseCase extends UserDetailsService {

    UserDetails signIn(String username);
}

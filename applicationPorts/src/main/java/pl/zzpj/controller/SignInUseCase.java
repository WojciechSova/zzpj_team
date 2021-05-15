package pl.zzpj.controller;

import org.springframework.security.core.userdetails.UserDetails;
import pl.zzpj.model.UserCredentials;

public interface SignInUseCase {

    UserDetails signIn(UserCredentials userCredentials);
}

package pl.zzpj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.zzpj.controller.SignInUseCase;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SignInUseCase signInUseCase;

    private final JWTRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(SignInUseCase signInUseCase, JWTRequestFilter jwtRequestFilter) {
        this.signInUseCase = signInUseCase;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(signInUseCase);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/error", "/api/all", "/api/auth/**", "/oauth2/**", "/index.html", "/*.js", "/*.js.map", "/*.css", "/assets/img/*.png", "/favicon.ico").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers("/accounts").hasAuthority("ADMIN")
                .antMatchers("/accounts/own").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/accounts/**").hasAuthority("ADMIN")
                .antMatchers("/transactions").hasAuthority("ADMIN")
                .antMatchers("/transactions/*").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/accounts/*").hasAnyAuthority("CLIENT", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

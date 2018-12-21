package ru.fonzy.fnotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import ru.fonzy.fnotes.service.UserService;
import ru.fonzy.fnotes.service.impls.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserServiceImpl userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/register", "/activate/*").permitAll()
                    .antMatchers("/register_email_sent", "/register_email_failed").permitAll()
                    .antMatchers("/pass_recover_email_sent", "/pass_recover_email_failed").permitAll()
//                    .antMatchers("/new_password").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .antMatchers("/notes/**").authenticated()
                    .antMatchers("/profile/**").authenticated()
                    .antMatchers("/users/**").hasAuthority("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/notes/all")
                    .failureUrl("/login_failed")
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                    .key("REYH3467BfDSFG")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID", "SESSION", "SPRING_SECURITY_REMEMBER_ME_COOKIE")
                    .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

    }

}

package hr.rezek.flightschool.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/about").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/login").anonymous()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                .and()
                // ignore CSRF protection and allow IFRAME for h2 console
                    .csrf().ignoringAntMatchers("/h2-console/**")
                    .and()
                .headers().frameOptions().sameOrigin();
    }
}
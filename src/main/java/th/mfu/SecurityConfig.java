package th.mfu;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**") // Allow public access
                .authenticated() // Require authentication for other requests
                .and()
                .csrf().disable() // Disable CSRF (http://www.baeldung.com/spring-security-csrf)
                .httpBasic(); // Enable Basic Authentication
    }
}

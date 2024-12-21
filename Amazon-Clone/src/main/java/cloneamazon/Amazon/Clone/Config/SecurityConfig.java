package cloneamazon.Amazon.Clone.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.Customizer;

import cloneamazon.Amazon.Clone.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
        private UserService userService;

    // @Autowired
    // private JwtFilter jwtFilter;
      @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.csrf(customizer -> customizer.disable());  
            http.authorizeHttpRequests(request -> request.requestMatchers("/user/register", "/user/login", "/login", "/user/all").permitAll().anyRequest().authenticated());
            http.formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
            // http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
            // http
            // .csrf(csrf -> csrf.disable())
            // .authorizeHttpRequests(request -> request
            //     .requestMatchers(
            //         "/user/register", 
            //         "/user/login",
            //         "/user/all",
            //         "/user/email/*",
            //         "/user/id/*"
            //     ).permitAll()
            //     .anyRequest().authenticated()
            // )
            // .httpBasic(Customizer.withDefaults());
        // return http.build();
        }
        @Bean
         AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
            provider.setUserDetailsService(userService);
            return provider;
        }

}

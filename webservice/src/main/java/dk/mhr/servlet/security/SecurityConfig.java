//package dk.mhr.servlet.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.authentication.builders.*;
//import org.springframework.security.config.annotation.web.configuration.*;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("Morten").password("Morten1234!").roles("USER");
//    }
//}
package com.cubicit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cubicit.service.UserSpringSecuirtyAuthProvider;

@Configuration 
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecuirtyConfiguration extends WebSecurityConfigurerAdapter { 
	
	
   @Autowired	
   private UserSpringSecuirtyAuthProvider userSpringSecurityAuthProvider;
   
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSpringSecurityAuthProvider).passwordEncoder(getPasswordEncoder());
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder(12);
   }
   
  @Override 
  protected void configure(HttpSecurity http) throws Exception {   
    
	http.authorizeRequests()//
      .antMatchers("/login").permitAll().
      antMatchers("/forgetPassword").permitAll(). 
      // Disallow everything else..
      anyRequest().authenticated();
      
    http.csrf().disable();
    //for login
    http.formLogin().failureUrl("/login?error=true")
    .loginPage("/login")
    .defaultSuccessUrl("/customer/dashboard")
    /*.failureUrl("/corp/auth?error=true")*/
    .and().exceptionHandling()
    .accessDeniedPage("/access/denied")
    .and()
    .logout().logoutUrl("/customer/logout")
    .logoutSuccessUrl("/oauth?error=true")
    .invalidateHttpSession(true) 
    .deleteCookies("JSESSIONID");
  } 
  
  
  private PasswordEncoder getPasswordEncoder() {
      return new PasswordEncoder() {
          @Override
          public String encode(CharSequence charSequence) {
              return charSequence.toString();
          }

          @Override
          public boolean matches(CharSequence charSequence, String s) {
              return true;
          }
      };
  }
  
  
  
 
  /*@Bean
  public AuthenticationFailureHandler customAuthenticationFailureHandler() {
      return new LoginFailureUserAuthHandler(springSecurityService);
  }*/
  
}
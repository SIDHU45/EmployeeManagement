package com.sid.CRUDOPERATIONS.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsManager manage(DataSource datsource) {
		JdbcUserDetailsManager manage = new JdbcUserDetailsManager(datsource);
		manage.setUsersByUsernameQuery("select phone_number, password, active from employee where phone_number=?" );
		manage.setAuthoritiesByUsernameQuery("select phone_number, role from employeerole where phone_number=?");		
		return manage;
		
	}	
//	@Bean
//	public InMemoryUserDetailsManager userDetails() {
//		
//		UserDetails sidhu = User.builder()
//				.username("sidhu")
//				.password("{noop}test123")
//				.roles("EMPLOYEE" , "MANAGER" , "ADMIN")
//				.build();
//		UserDetails john = User.builder()
//				.username("john")
//				.password("{noop}test123")
//				.roles("EMPLOYEE" , "MANAGER")
//				.build();
//		UserDetails mary = User.builder()
//				.username("mary")
//				.password("{noop}test123")
//				.roles("EMPLOYEE")
//				.build();
//		return new InMemoryUserDetailsManager(sidhu , john , mary) ;
//	}
	
	@Bean
	public SecurityFilterChain fiterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(configurer ->
										configurer
										.requestMatchers("/deleteEmployee/**" , "/showform/**").hasRole("ADMIN")
										.requestMatchers("/showupdate/**").hasRole("MANAGER")
										.requestMatchers("/list").hasRole("EMPLOYEE")
										.requestMatchers("/signup_page" , "/register" , "/").permitAll()
										.anyRequest().authenticated()
								)
						.formLogin(form ->
										form 
											.loginPage("/login_page")
											.defaultSuccessUrl("/list")
											.loginProcessingUrl("/loginprocess")
											.permitAll()
								)
						
						.exceptionHandling(configurer ->
										configurer.accessDeniedPage("/access_denied_page")
						)
						.logout(logout -> logout.permitAll()
						             );
		
		return security.build();
	}
}

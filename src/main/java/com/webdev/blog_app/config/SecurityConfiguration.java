package com.webdev.blog_app.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.http.HttpMethod;


//import com.security.rest_api_security_01.service.MyUserDetailsService;
// 
 


@Configuration
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfiguration{
	
	public static final String[] PUBLIC_URLS = {
		    "/api/users/register", 
		    "/api/users/login", 
		    "/api/email/send",
		    "/api/users/forgotpassword",
		    "/api/users/resetpassword", 
		    "/v3/api-docs/**",
		    "/v2/api-docs/**",
		    "/swagger-ui/**", 
		    "/swagger-resources/**", 
		    "/webjars/**"
		};
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Autowired
	    private JwtFilter jwtFilter;
	 
	 
    
//	@Bean
//	
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		 return
//				 http.
//				      csrf(customiser->customiser.disable())  // disable cross site request forgery
//				      .authorizeHttpRequests(request->request.requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated()) // authorize all requests
//				      .httpBasic(Customizer.withDefaults())   // use http basic authentication
//				      //.formLogin(Customizer.withDefaults())
//				      .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // every request should be stateless and no session should be created
//				      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//				      .build()
//				 ;
//	}
	 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     return http
	         .csrf(csrf -> csrf.disable())
	         .authorizeHttpRequests(auth -> auth
	             .requestMatchers(PUBLIC_URLS).permitAll()
	             .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() // allow only GETs on /api/categories
	             .anyRequest().authenticated() // all other requests need authentication
	         )
	         .httpBasic(Customizer.withDefaults())
	         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	         .build();
	 }

	
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1 = User.withDefaultPasswordEncoder().username("preety").password("password").roles("USER")
//				.build();
//		UserDetails user2 = User.withDefaultPasswordEncoder().username("apriti").password("password").roles("USER", "ADMIN").build();
//		
//		 return new InMemoryUserDetailsManager(user1, user2);
//	}
 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return  config.getAuthenticationManager();
	}
	 

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	
	@Bean
	public FilterRegistrationBean coreFilter() {
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		CorsConfiguration cors=new CorsConfiguration();
		cors.setAllowCredentials(true);
		cors.addAllowedOriginPattern("*");
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*"); 
		cors.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", cors);
		
		FilterRegistrationBean bean=new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(-110);
		return bean;
		
		
	}
	 
    
}

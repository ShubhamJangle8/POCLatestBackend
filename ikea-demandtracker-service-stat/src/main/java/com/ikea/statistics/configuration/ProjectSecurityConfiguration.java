package com.ikea.statistics.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				String[] origins = { "http://ec2-3-219-32-49.compute-1.amazonaws.com:3000", "http://localhost:3000" };
				List<String> originLi = new ArrayList<>(Arrays.asList(origins));
				config.setAllowedOrigins(originLi);
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().authorizeRequests().antMatchers("/api/stats/req-count").hasAnyRole("REQ", "PMO")
				.antMatchers("/api/stats/chart-data/**").hasAnyRole("REQ", "PMO").antMatchers("/api/stats/detail-data")
				.hasAnyRole("REQ", "PMO").and().csrf().disable().oauth2ResourceServer().jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter);

		http.headers().frameOptions().sameOrigin();
	}
}

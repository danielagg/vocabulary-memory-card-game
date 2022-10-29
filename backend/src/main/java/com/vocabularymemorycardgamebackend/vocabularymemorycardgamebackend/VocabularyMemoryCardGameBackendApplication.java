package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class VocabularyMemoryCardGameBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocabularyMemoryCardGameBackendApplication.class, args);
	}

	@EnableWebSecurity
	public class WebSecurityConfig {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors();
			http.csrf().disable();
			return http.build();
		}
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080/", "http://localhost:5173/")); // Arrays.asList("https://example.com")
		configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PATCH","OPTIONS"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

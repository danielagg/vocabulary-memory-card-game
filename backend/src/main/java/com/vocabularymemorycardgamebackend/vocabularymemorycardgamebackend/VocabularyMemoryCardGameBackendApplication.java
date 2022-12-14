package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
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

//			http
//					.authorizeRequests()
//					.antMatchers("/api/flashcard/**")
//					.permitAll()
//					.anyRequest()
//					.authenticated();

//			http
//					.oauth2Client(oauth2 -> oauth2
//							.clientRegistrationRepository(this.clientRegistrationRepository())
//							.authorizedClientRepository(this.authorizedClientRepository())
//							.authorizedClientService(this.authorizedClientService())
//							.authorizationCodeGrant(codeGrant -> codeGrant
//									.authorizationRequestRepository(this.authorizationRequestRepository())
//									.authorizationRequestResolver(this.authorizationRequestResolver())
//									.accessTokenResponseClient(this.accessTokenResponseClient())
//							)
//					);


			return http.build();
		}


	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080/", "http://localhost:5173/", "https://flashcards.danielagg.com/"));
		configuration.setAllowedMethods(List.of("GET","POST","DELETE","PATCH","OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("content-type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

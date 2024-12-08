package klu.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



	@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Allow all paths
	            .allowedOrigins("http://localhost:3000") // Allow only requests from your React frontend
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all relevant HTTP methods
	            .allowedHeaders("*") // Allow all headers
	            .allowCredentials(true) // If you use cookies or authentication headers, enable credentials
	            .maxAge(3600); // Cache preflight response for 1 hour
	    }
	    
	    
	}





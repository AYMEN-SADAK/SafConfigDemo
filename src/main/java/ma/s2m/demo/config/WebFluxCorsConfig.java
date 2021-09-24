package ma.s2m.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxCorsConfig implements WebFluxConfigurer {

	@Value("${cors.allowedAdresses}")
	private List<String> corsAllowedAddresses;

	@Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
		String[] allowedadresses = new String [corsAllowedAddresses.size()];
		corsRegistry.addMapping("/**")
				.allowedOrigins(corsAllowedAddresses.toArray(allowedadresses))
				.allowedMethods("*");
    }
}
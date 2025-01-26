package com.becoder.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI myOpenApi() {
		OpenAPI openAPI = new OpenAPI();
		Info info = new Info();
		info.setTitle("Enotes API");
		info.setDescription("Enotes API Documentation");
		info.version("1.0,.0");
		info.setTermsOfService("http://enotes.com/terms");
		info.contact(new Contact().name("Pabitra Das").email("becoder@enotes.com").url("http://enotes.com/contact"));
		info.license(new License().name("Enoted 2.0").url("http://enotes.com"));

		List<Server> server = List.of(new Server().description("Dev").url("http://localhost:8080"),
				new Server().description("Test").url("http://localhost:8081"),
				new Server().description("Prod").url("http://localhost:8082"));

		SecurityScheme ss = new SecurityScheme().name("Authorization").scheme("bearer").type(Type.HTTP)
				.bearerFormat("JWT").in(In.HEADER);
		Components component = new Components().addSecuritySchemes("Token", ss);

		openAPI.setInfo(info);
		openAPI.setServers(server);
		openAPI.setComponents(component);
		openAPI.setSecurity(List.of(new SecurityRequirement().addList("Token")));
		return openAPI;
	}

}

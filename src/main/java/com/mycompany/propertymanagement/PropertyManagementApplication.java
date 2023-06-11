package com.mycompany.propertymanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@OpenAPIDefinition
public class PropertyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyManagementApplication.class, args);
	}

	@Bean
	public WebMvcRequestHandlerProvider webMvcRequestHandlerProvider(
			Optional<ServletContext> context,
			HandlerMethodResolver methodResolver,
			List<RequestMappingInfoHandlerMapping> handlerMappings) {
		handlerMappings = handlerMappings.stream()
				.filter(rh -> rh.getClass().getName().contains("RequestMapping")).toList();
		return new WebMvcRequestHandlerProvider(context, methodResolver, handlerMappings);
	}
}

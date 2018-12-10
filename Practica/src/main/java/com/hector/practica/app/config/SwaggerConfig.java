package com.hector.practica.app.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean

	public Docket usersApi() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(usersApiInfo()).select().paths(userPaths())
				.apis(RequestHandlerSelectors.any()).build().useDefaultResponseMessages(false);

	}

	private ApiInfo usersApiInfo() {

		return new ApiInfo("Servicio Api Rest de pedidos artículos",
				"Servicio que permite listar los pedidos y artículos asi como buscar los detalles de un pedido o artículo concreto asi como añadir modificar o eliminar un pedido",
				"0.1", "Terminos del servicio", new Contact("Hector Ordoñez Castañon", "", "etormoredador@gmail.com"),
				"", "");
	}

	private Predicate<String> userPaths() {

		return regex("/api.*");

	}

}
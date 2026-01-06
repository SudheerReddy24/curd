package com.curd.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "CRUD Api",
				description = "Performing CRUD application operations",
				termsOfService = "CRUD Terms of service",
				license = @License(name = "CRUD Licence"),
				contact = @Contact(name = "Sudheer Reddy",
						url = "http://localhost:5000/crud",
						email = "chintu@gmail.com" ),
				version = "v1"
		)
)
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}

package com.eazybytes.acounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Management API", version = "1.0", description = "Documentation Accounts Management API v1.0", contact = @Contact(name = "Shashi Kumari", email = "shashi@example.com", url = "http://www.example.com"), license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")), externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(description = "Springdoc OpenAPI 3.0", url = "https://springdoc.org/"))
public class AcountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcountsApplication.class, args);
    }

}

package com.rabobank.lostandfound.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =
@Info(title = "Lost & Found application",
        version = "1.0",
        description = "In this application that reads lost items from a file (e.g. PDF) provided by\n" +
                "Admin and stores the extracted information. Users of this application can read the stored lost\n" +
                "items and claim it.",
        contact = @Contact(
                name = "Support",
                url = "http://localhost:9092/swagger-ui/index.html",
                email = "aichmonali@outlook.com"
        )),
servers ={@Server(
                description = "Dev env",
                url = "http://localhost:9092"),
        @Server(
                description = "Prod env",
                url = "http://localhost:9093")}
)
public class OpenApiConfig {
}

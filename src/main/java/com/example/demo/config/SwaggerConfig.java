package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Server httpsServer = new Server();
        httpsServer.setUrl("https://9314.pro604cr.amypo.ai");
        httpsServer.setDescription("HTTPS Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Grid Load Shedding API")
                        .version("1.0")
                        .description("Load shedding, zones, demand & forecast APIs"))
                .servers(List.of(httpsServer));
    }
}

package br.com.fiap.ms_pagamento.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration


public class OpenApiConfig {
    @Bean
    public OpenAPI apiProdutos(){
        return  new OpenAPI()
                .info(new Info()
                        .title("Api de cadastro de Pagamentos")
                        .description("Projeto de referencia - API-´Pagamentos")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/LeonardoGomess/ms-pagamento")));
    }

}

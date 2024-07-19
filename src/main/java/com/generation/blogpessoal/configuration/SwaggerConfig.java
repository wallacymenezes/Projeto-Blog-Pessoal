package com.generation.blogpessoal.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springdoc.core.customizers.OpenApiCustomizer;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springBlogPessoalOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Blog Pessoal")
                        .description("API do Projeto de blog pessoal - Generation Brasil")
                        .version("v0.1.0")
                        .license(new License()
                                .name("Generation Brasil")
                                .url("https://brazil.generation.org/"))
                        .contact(new Contact()
                                .name("Wallacy Menezes")
                                .url("https://www.linkedin.com/in/wallacy-menezes/")
                                .email("wallacy.santos@generation.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/wallacymenezes/Projeto-Blog-Pessoal"));
    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
                    .forEach(operation -> {

                            ApiResponses apiResponses = operation.getResponses();

                            apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                            apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                            apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                            apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
                            apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
                            apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
                            apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                            apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
                    }));
        };
    }

    private ApiResponse createApiResponse(String description) {
        return new ApiResponse().description(description);
    }
}

package com.example.finalprojectdesc;

import com.example.finalprojectdesc.util.ReadJsonFileToJsonObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import java.io.IOException;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

    ReadJsonFileToJsonObject readJsonFileToJsonObject = new ReadJsonFileToJsonObject();

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        ApiResponse badRequestAPI = new ApiResponse()
                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example()
                                        .value(readJsonFileToJsonObject.read().get("badRequestResponse").toString()))))
                .description("Bad Request");
       ApiResponse internalServerErrorResponseAPI = new ApiResponse().content(
                        new Content()
                                .addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                                new Example()
                                                        .value(readJsonFileToJsonObject.read().get("internalServerErrorResponse").toString()))))
                .description("Internal Server Error");
       ApiResponse notFoundResponseAPI = new ApiResponse()
                .content(new Content()
                        .addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                        new Example()
                                                .value(readJsonFileToJsonObject.read().get("notFoundResponse").toString()))))
                .description("Not Found");
        Components components = new Components();
        components.addResponses("badRequestAPI", badRequestAPI);
        components.addResponses("internalServerErrorResponseAPI", internalServerErrorResponseAPI);
        components.addResponses("notFoundResponseAPI", notFoundResponseAPI);
        return new OpenAPI()
                .info(new Info().title("Rent a car").version("1.0.0"))
                .components(components)
                .externalDocs(new ExternalDocumentation()
                        .description("Rent a car external documentation")
                        .url("https://bit.ly/rent-a-car-sunny"));
    }
}
package software.locus.tryout.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI locusTryoutOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Locus Custom Software Tryout")
                .description("API Rest that provides a CRUD with States and Cities")
                .version("FINAL-RELEASE")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/license.html")))
                .externalDocs(new ExternalDocumentation()
                .description("Locus Tryout Repository")
                .url("https://github.com/crespo/locus-tryout"));
    }

}

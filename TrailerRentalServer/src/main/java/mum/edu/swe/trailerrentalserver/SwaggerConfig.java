package mum.edu.swe.trailerrentalserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * localhost:9999/v2/api-docs
     * localhost:9999/swagger-ui.html
     * @return
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2);
    }


}

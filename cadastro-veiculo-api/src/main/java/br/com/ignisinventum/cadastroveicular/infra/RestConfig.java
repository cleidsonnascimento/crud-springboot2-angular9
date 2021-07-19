package br.com.ignisinventum.cadastroveicular.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class RestConfig {

	@Bean
	public Docket apiAutenticacaoAuto() {
		
		//@formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("veicular-api")
				.apiInfo(baseApiInfo()
						.title("API para cadastro veicular")
						.description("DEMO de uma api para cadastro veicular.")
						.build())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/.*"))
				.build();
		//@formatter:on
	}
	
	private ApiInfoBuilder baseApiInfo() {
		//@formatter:off
		return new ApiInfoBuilder()
				.termsOfServiceUrl("http://www.ignisinventum.com.br")
				.contact(new Contact("Ignis Inventum", "http://www.ignisinventum.com.br", "cleidson.nascimento@ignisinventum.com.br"))
				.version("1.0");
		//@formatter:on
	}

}

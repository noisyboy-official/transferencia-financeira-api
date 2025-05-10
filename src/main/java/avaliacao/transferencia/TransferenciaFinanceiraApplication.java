package avaliacao.transferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Classe principal da aplicação Transferência Financeira.
 * Esta classe inicializa a aplicação Spring Boot e configura as opções de CORS.
 */
@SpringBootApplication
public class TransferenciaFinanceiraApplication {

	@Autowired
	private Environment env;

	/**
	 * Ponto de entrada da aplicação Spring Boot.
	 *
	 * @param args Argumentos de linha de comando passados durante a inicialização da aplicação.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TransferenciaFinanceiraApplication.class, args);
	}

	/**
	 * Configura as opções de Compartilhamento de Recursos de Origem Cruzada (CORS) para a aplicação.
	 *
	 * @return Uma instância de WebMvcConfigurer com mapeamentos CORS configurados.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String urls = env.getProperty("cors.urls");
				CorsRegistration reg = registry.addMapping("/**");
			}
		};
	}

}

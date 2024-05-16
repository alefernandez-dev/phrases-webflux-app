package dev.alejandro.phraseswebfluxapp;

import dev.alejandro.phraseswebfluxapp.phrase.PhraseHandler;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringBootApplication
public class PhrasesWebFluxAppApplication {

	@Bean
	RouterFunction<ServerResponse> route(PhraseHandler phraseHandler) {
		return RouterFunctions.route()
				.GET("/phrases", accept(MediaType.APPLICATION_JSON), phraseHandler::getPhrases)
				.GET("/phrases/{nationality}", accept(MediaType.APPLICATION_JSON), phraseHandler::getPhrasesByAuthorNationality)
				.build();
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		var connectionFactoryInitializer = new ConnectionFactoryInitializer();
		connectionFactoryInitializer.setConnectionFactory(connectionFactory);
		connectionFactoryInitializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));

		return connectionFactoryInitializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(PhrasesWebFluxAppApplication.class, args);
	}

}

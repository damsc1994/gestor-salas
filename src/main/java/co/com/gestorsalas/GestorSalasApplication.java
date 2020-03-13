package co.com.gestorsalas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.gestorsalas.entity.Sala;
import co.com.gestorsalas.repository.SalasRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class GestorSalasApplication {
	
	public static void main(String args[]) {
		SpringApplication.run(GestorSalasApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner init(SalasRepository repository){
	        return args -> {

	            Flux<Sala> books = Flux.just(
	                    Sala.builder().capacidad("200").horaDisponible("08:00:00")
	                    .nombre("Sala1").piso("2").build(),
	                    Sala.builder().capacidad("200").horaDisponible("10:00:00")
	                    .nombre("Sala2").piso("1").build()
	            ).flatMap(p-> repository.save(p));

	            books.thenMany(repository.findAll())
	                    .subscribe(System.out::println);

	        };
	    }
}

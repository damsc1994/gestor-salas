package co.com.gestorsalas.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.com.gestorsalas.entity.Sala;
import reactor.core.publisher.Mono;

public interface SalasRepository extends ReactiveMongoRepository<Sala, String>{
	
	public Mono<Sala> findByNombre(String nombre);

}

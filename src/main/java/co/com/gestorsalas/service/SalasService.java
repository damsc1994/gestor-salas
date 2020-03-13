package co.com.gestorsalas.service;

import org.springframework.stereotype.Service;

import co.com.gestorsalas.entity.Sala;
import co.com.gestorsalas.repository.SalasRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SalasService {
	
	private SalasRepository salasRepository;
	
	public Mono<Sala> findById(String id) {
		return salasRepository.findById(id).switchIfEmpty(Mono.empty());
	}
	
	public Mono<Sala> finByName(String nombre) {
		return salasRepository.findByNombre(nombre);
	}
	
	public Mono<Sala> save(Sala sala) {
		return salasRepository.save(sala);
	}
	
	public Flux<Sala> findAll() {
		return salasRepository.findAll();
	}
	
	public Mono<Sala> deleteById(String id) {
		return findById(id).map(sala -> {
			salasRepository.deleteById(sala.getId()).subscribe();
			return sala;
		}).switchIfEmpty(Mono.empty());
	}
	
	public Mono<Long> deleteAll() {
		return salasRepository.count().map(salas -> {
			if (salas == 0) return 0L;
			salasRepository.deleteAll().subscribe();
			return salas;
		});
		
	}
}

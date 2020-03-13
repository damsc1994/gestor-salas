package co.com.gestorsalas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gestorsalas.entity.Error;
import co.com.gestorsalas.entity.Sala;
import co.com.gestorsalas.service.SalasService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("salas")
public class SalaController {
	
	private SalasService salaServices;
	
	@GetMapping("/prueba")
	public ResponseEntity<String> prueba() {
		return ResponseEntity.ok("Hola Mundo");
	}
	
	@GetMapping("/")
	public Mono<ResponseEntity<Flux<Sala>>> getSalas() {
		return Mono.just(ResponseEntity.ok(salaServices.findAll()));
	}
	
	@GetMapping("/find-id/{id}")
	public Mono<ResponseEntity<Sala>> getSalas(@PathVariable String id) {
		return salaServices.findById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(this.error(404, "No se encontro registro con el id digitado"));
	}
	
	@GetMapping("/find-name/{name}")
	public Mono<ResponseEntity<Sala>> getByName(@PathVariable String name) {
		return salaServices.finByName(name).map(ResponseEntity::ok)
				.defaultIfEmpty(this.error(404, "No se encontro registro con el nombre digitado"));
	}
	
	@PostMapping("/save")
	public Mono<ResponseEntity<Sala>> save(@RequestBody Sala sala) {
		return salaServices.save(sala).map(ResponseEntity::ok);
	}
	
	@DeleteMapping("/delete-id/{id}")
	public Mono<ResponseEntity<Sala>> deleteById(@PathVariable String id) {
		return salaServices.deleteById(id).map(ResponseEntity::ok).
				defaultIfEmpty(this.error(404, "No se encontro el registro que quiere eliminar"));
	}
	
	@DeleteMapping("/deletes")
	public Mono<ResponseEntity<String>> deleteAll() {
		return salaServices.deleteAll().map(salas -> {
			if (salas == 0) return ResponseEntity.ok("No se encontraron salas a eliminar");
			return ResponseEntity.ok("Se eliminaron " + salas + " salas");
		});
	}
	
	public ResponseEntity<Sala> error(int code, String message) {
		Sala sala = new Sala().toBuilder().error(new Error().toBuilder().code(code).message(message).build()).build();
		return ResponseEntity.status(code).body(sala);
	}
	
}

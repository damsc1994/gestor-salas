
package co.com.gestorsalas.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class Sala {
	
	@Id
	private String id;
	private String piso;
	private String capacidad;
	private String nombre;
	private String horaDisponible;
	private Error error;
}

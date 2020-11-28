package com.ceiba.laboratorio.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDTO<T> {
	private String mensaje;
	private T data;
	
	public static <T> RespuestaDTO<T> ok(T data, String mensaje) {
		return RespuestaDTO.<T>builder()
				.data(data)
				.mensaje(mensaje)
				.build();
	}
	
}

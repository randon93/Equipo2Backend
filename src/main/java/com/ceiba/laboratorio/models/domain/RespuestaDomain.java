package com.ceiba.laboratorio.models.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDomain<T> {
	private boolean status;
	private String mensaje;
	private T data;
	
	public static <T> RespuestaDomain<T> ok(T data, String mensaje, boolean status) {
		return RespuestaDomain.<T>builder()
				.data(data)
				.mensaje(mensaje)
				.status(status)
				.build();
	}
	
}

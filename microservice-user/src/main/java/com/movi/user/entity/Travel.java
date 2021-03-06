package com.movi.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "travel")
public class Travel extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	private String id;

	/**
	 * Conductor de vehículo de una empresa
	 */
	private BusinessDriverVehicle businessDriverVehicle;

	/**
	 * Pasajero que pidio el viaje
	 */
	private User passenger;

	/**
	 * Tipo de viaje
	 */
	private String travelType;
	
	/**
	 * Estado del viaje
	 */
	private String status;

	/**
	 * ======================================================================================================
	 * Distancia, Tiempo y Precio
	 * ======================================================================================================
	 */
	/**
	 * Distancia total de la ruta de origen a destino
	 */
	private double totalDistance;
	
	/**
	 * Texto de la distancia total de la ruta de origen a destino
	 */
	private String totalDistanceText;
	
	/**
	 * Tiempo total de la ruta de origen a destino
	 */
	private double totalTime;
	
	/**
	 * Texto del tiempo total de la ruta de origen a destino
	 */
	private String totalTimeText;

	/**
	 * Precio total de la ruta de origen a destino
	 */
	private double totalPrice;

	/**
	 * Fecha y hora en que se recoge al pasajero
	 */
	private LocalDateTime dateInitial;

	/**
	 * Fecha y hora en que se llega al destino
	 */
	private LocalDateTime dateFinal;

	/**
	 * Fecha y hora de creación del viaje
	 */
	private LocalDateTime dateRegister;
	
	/**
	 * Polyline para renderizar la ruta
	 */
	private String routePolyline;

	/**
	 * ======================================================================================================
	 * Direcciones
	 * ======================================================================================================
	 */

	/**
	 * Dirección de origen
	 */
	private String addressInitial;

	/**
	 * Dirección de destino
	 */
	private String addressFinal;

	/**
	 * Dirección de destino sugerido
	 */
	private String addressSuggested;

	/**
	 * ======================================================================================================
	 * Coordenadas
	 * ======================================================================================================
	 */

	/**
	 * Latitud origen
	 */
	private String latInitial;

	/**
	 * Longitud origen
	 */
	private String lngInitial;

	/**
	 * Latitud destino
	 */
	private String latFinal;

	/**
	 * Longitud destino
	 */
	private String lngFinal;

	/**
	 * Latitud destino sugerido
	 */
	private String latFinalSuggested;

	/**
	 * Longitud destino sugerido
	 */
	private String lngFinalSuggested;

	/**
	 * ======================================================================================================
	 * Pasajeros
	 * ======================================================================================================
	 */	
	/**
	 * Número de pasajeros adultos
	 */
	private int numberAdults;
	/**
	 * Número de pasajeros niños
	 */
	private int numberChildren;
	
	/**
	 * Lista de métodos de pago
	 */
	private List<String> paymentMethods;
//	@PrePersist
//	void prePerist() {
//		dateRegister = LocalDateTime.now();
//	}
}
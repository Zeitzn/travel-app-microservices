package com.movi.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TravelDto {
	
	private String id;
	
	private BusinessDriverVehicleDto businessDriverVehicle;
	
	private UserDto passenger;

	@Schema(description = "Travel type", example = "INDIVIDUAL", required = true)
	@NotBlank(message = "Enter travelType")
	@NotEmpty(message = "Enter travelType")
	@Size(min = 4, max = 20, message = "The travelType must contain {min} to {max} characters")
	private String travelType;
	
	@Schema(description = "Travel status", example = "INDIVIDUAL", required = true)
	@NotBlank(message = "Enter status")
	@NotEmpty(message = "Enter status")
	@Size(min = 3, max = 20, message = "The status must contain {min} to {max} characters")
	private String status;

	@Schema(description = "Total distance in meters", example = "", required = true)
	@NotNull(message = "Enter totalDistance")
	private double totalDistance;
	
	@Schema(description = "Total distance text", example = "", required = true)
	@NotNull(message = "Enter totalDistanceText")
	private String totalDistanceText;	
	
	@Schema(description = "Total time in seconds", example = "", required = true)
	@NotNull(message = "Enter totalTime")
	private double totalTime;
	
	@Schema(description = "Total distance text", example = "", required = true)
	@NotNull(message = "Enter totalTimeText")
	private String totalTimeText;

	@Schema(description = "Total price", example = "", required = true)
	@NotNull(message = "Enter totalPrice")
	private double totalPrice;
	
	@Schema(description = "Route Polyline", example = "", required = true)
	@NotNull(message = "Enter routePolyline")
	private String routePolyline;

	@Schema(description = "Date in init travel", example = "", required = true)
//	@NotBlank(message = "Enter dateInitial")
//	@NotEmpty(message = "Enter dateInitial")
	private LocalDateTime dateInitial;

	@Schema(description = "Date in finished travel", example = "", required = true)
//	@NotBlank(message = "Enter dateFinal")
//	@NotEmpty(message = "Enter dateFinal")
	private LocalDateTime dateFinal;

	@Schema(description = "Date in register travel", example = "", required = true)
	private LocalDateTime dateRegister;

	@Schema(description = "Initial address", example = "", required = true)
	@NotBlank(message = "Enter addressInitial")
	@NotEmpty(message = "Enter addressInitial")
	@Size(min = 1, max = 250, message = "The addressInitial must contain {min} to {max} characters")
	private String addressInitial;

	@Schema(description = "Final address", example = "", required = true)
	@NotBlank(message = "Enter addressFinal")
	@NotEmpty(message = "Enter addressFinal")
	@Size(min = 1, max = 250, message = "The addressFinal must contain {min} to {max} characters")
	private String addressFinal;

	@Schema(description = "Suggested address", example = "", required = true)
	@NotBlank(message = "Enter addressSuggested")
	@NotEmpty(message = "Enter addressSuggested")
	@Size(min = 1, max = 250, message = "The addressSuggested must contain {min} to {max} characters")
	private String addressSuggested;

	@Schema(description = "Initial address latitude", example = "", required = true)
	@NotBlank(message = "Enter latInitial")
	@NotEmpty(message = "Enter latInitial")
	@Size(min = 1, max = 250, message = "The latInitial must contain {min} to {max} characters")
	private String latInitial;
	
	@Schema(description = "Initial address longitude", example = "", required = true)
	@NotBlank(message = "Enter lngInitial")
	@NotEmpty(message = "Enter lngInitial")
	@Size(min = 1, max = 250, message = "The lngInitial must contain {min} to {max} characters")
	private String lngInitial;

	@Schema(description = "Final address latitude", example = "", required = true)
	@NotBlank(message = "Enter latFinal")
	@NotEmpty(message = "Enter latFinal")
	@Size(min = 1, max = 250, message = "The latFinal must contain {min} to {max} characters")
	private String latFinal;

	@Schema(description = "Final address longitude", example = "", required = true)
	@NotBlank(message = "Enter lngFinal")
	@NotEmpty(message = "Enter lngFinal")
	@Size(min = 1, max = 250, message = "The lngFinal must contain {min} to {max} characters")
	private String lngFinal;

	@Schema(description = "Final suggested address latitude", example = "", required = true)
	@NotBlank(message = "Enter latFinalSuggested")
	@NotEmpty(message = "Enter latFinalSuggested")
	@Size(min = 1, max = 250, message = "The latFinalSuggested must contain {min} to {max} characters")
	private String latFinalSuggested;

	@Schema(description = "Final suggested address longitude", example = "", required = true)
	@NotBlank(message = "Enter lngFinalSuggested")
	@NotEmpty(message = "Enter lngFinalSuggested")
	@Size(min = 1, max = 250, message = "The lngFinalSuggested must contain {min} to {max} characters")
	private String lngFinalSuggested;
	
	@Schema(description = "Total number of adults passengers", example = "1", required = true)
	@NotNull(message = "Enter numberAdults")
	private int numberAdults;
	
	@Schema(description = "Total number of children passengers", example = "0", required = true)
	@NotNull(message = "Enter numberChildren")
	private int numberChildren;
	
	@Schema(description = "Payment methods", example = "", required = true)
	@NotEmpty(message = "Enter paymentMethods")
	private List<String> paymentMethods;
}

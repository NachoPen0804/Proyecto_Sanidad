package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.cheste.ad_sanidad_di.DTO.VisitaResponseDTO;
import es.cheste.ad_sanidad_di.model.Visita;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitaApiCliente {
	private final HttpClient client = HttpClient.newHttpClient();
	private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	private final String baseUrl = "http://localhost:8080/api/visitas";

	public void create(Long pacienteId, Long medicoId, LocalDate fecha, int hora, int minuto) {
		try {
			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("pacienteId", pacienteId);
			requestBody.put("medicoId", medicoId);
			requestBody.put("fecha", fecha);
			requestBody.put("hora", hora);
			requestBody.put("minuto", minuto);

			String json = mapper.writeValueAsString(requestBody);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();

			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Otros métodos (sin cambios relevantes para esta query)
	public List<Visita> obtenerVisitas() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return mapper.readValue(response.body(), new TypeReference<List<Visita>>() {});
			} else {
				// Lanzar una excepción con el mensaje de error
				throw new RuntimeException("Error al obtener visitas: " + response.statusCode() + " - " + response.body());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}
	public void delete(long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.DELETE()
				.build();
		try {
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Visita obtenerVisitaPorId(long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.GET()
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), Visita.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void update(Visita visita) {
		try {
			String json = mapper.writeValueAsString(visita);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl + "/" + visita.getId()))
					.header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(json))
					.build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
}
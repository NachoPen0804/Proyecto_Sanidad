package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

	public List<Visita> obtenerVisitas() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), new TypeReference<List<Visita>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

	public void create(Long pacienteId, Long medicoId, LocalDate fecha) {
		try {
			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("pacienteId", pacienteId);
			requestBody.put("medicoId", medicoId);
			requestBody.put("fecha", fecha);

			String json = mapper.writeValueAsString(requestBody);
			System.out.println("JSON Enviado: " + json);

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

	public void delete(Long id) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl + "/" + id))
					.DELETE()
					.build();

			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Visita obtenerVisitaPorId(Long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.body() == null || response.body().isEmpty()) {
				return null;
			}
			return mapper.readValue(response.body(), Visita.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}

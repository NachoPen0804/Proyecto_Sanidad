package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cheste.ad_sanidad_di.model.Paciente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class PacienteApiClient {
	private final HttpClient client = HttpClient.newHttpClient();
	private final String baseUrl = "http://localhost:8080/api/pacientes";
	private final ObjectMapper mapper = new ObjectMapper();

	public List<Paciente> obtenerPacientes() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), new TypeReference<List<Paciente>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public void create(Paciente paciente) {
		try {
			String json = mapper.writeValueAsString(paciente);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Paciente obtenerPacientePorId(long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.body() == null || response.body().isEmpty()) {
				return null;
			}
			return mapper.readValue(response.body(), Paciente.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}

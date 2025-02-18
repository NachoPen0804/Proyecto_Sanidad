package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cheste.ad_sanidad_di.model.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

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
	private final RestTemplate restTemplate = new RestTemplate();


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
	public Paciente verificarPaciente(long id, String password) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id + "/password?password=" + password))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return mapper.readValue(response.body(), Paciente.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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


	public Paciente crearPaciente(Paciente paciente) {
		try {
			ResponseEntity<Paciente> response = restTemplate.postForEntity(baseUrl, paciente, Paciente.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				throw new RuntimeException("Error al crear el paciente: " + response.getStatusCode());
			}
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new RuntimeException("Error al crear el paciente: " + e.getResponseBodyAsString(), e);
		} catch (Exception e) {
			throw new RuntimeException("Error al crear el paciente", e);
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

	public void actualizarPaciente(Paciente paciente) {
		try {
			String json = mapper.writeValueAsString(paciente);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl + "/" + paciente.getId()))
					.header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(json))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				throw new RuntimeException("Error al actualizar el paciente: " + response.body());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar el paciente", e);
		}
	}
	public void eliminarPaciente(long id) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl + "/" + id))
					.DELETE()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 204) {
				throw new RuntimeException("Error al eliminar el paciente: " + response.body());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar el paciente", e);
		}
	}
}

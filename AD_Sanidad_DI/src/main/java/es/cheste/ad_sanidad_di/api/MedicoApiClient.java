package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.cheste.ad_sanidad_di.DTO.MedicoUpdateDTO;
import es.cheste.ad_sanidad_di.model.Medico;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class MedicoApiClient {
	private final HttpClient client = HttpClient.newHttpClient();
	private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	private final String baseUrl = "http://localhost:8080/api/medicos";
	

	public List<Medico> obtenerMedicos() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl))
					.GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return mapper.readValue(response.body(), new TypeReference<List<Medico>>() {});
			} else {
				throw new RuntimeException("Error al obtener médicos: " + response.body());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener médicos", e);
		}
	}
	
	public void create(Medico medico) {
		try {
			String json = mapper.writeValueAsString(medico);
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

	public Medico obtenerMedicoPorId(long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), Medico.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Medico verificarMedico(long id, String password) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id + "?password=" + password))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return mapper.readValue(response.body(), Medico.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void update(Medico medico) {
		try {
			MedicoUpdateDTO dto = new MedicoUpdateDTO(medico); // Convertir a DTO
			String json = mapper.writeValueAsString(dto);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl + "/" + medico.getId()))
					.header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(json))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

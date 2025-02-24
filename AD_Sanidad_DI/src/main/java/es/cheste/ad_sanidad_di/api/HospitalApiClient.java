package es.cheste.ad_sanidad_di.api;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.cheste.ad_sanidad_di.model.Hospital;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class HospitalApiClient{
	private final HttpClient client = HttpClient.newHttpClient();
	private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	private final String baseUrl = "http://localhost:8080/api/hospitales";
	
	public List<Hospital> obtenerPacientes(){
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), new TypeReference<List<Hospital>>() {});
		}catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	public void create(Hospital hospital) {
		try {
			String json = mapper.writeValueAsString(hospital);
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
	public Hospital obtenerHospitalPorId(long id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/" + id))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return mapper.readValue(response.body(), Hospital.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

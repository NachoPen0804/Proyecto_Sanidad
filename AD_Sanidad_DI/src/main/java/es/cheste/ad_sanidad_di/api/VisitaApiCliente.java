package es.cheste.ad_sanidad_di.api;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.cheste.ad_sanidad_di.model.Visita;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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
			return mapper.readValue(response.body(), new TypeReference<List<Visita>>() {});
		} catch (Exception e) {
			e.printStackTrace();
			return List.of(); // Devuelve una lista vacía en caso de error
		}
	}
	

	
}

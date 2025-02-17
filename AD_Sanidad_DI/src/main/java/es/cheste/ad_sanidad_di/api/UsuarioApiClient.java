package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cheste.ad_sanidad_di.model.Usuario;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class UsuarioApiClient {
	private final HttpClient client = HttpClient.newHttpClient();
	private final String baseUrl = "http://localhost:8080/api/pacientes";
	private final ObjectMapper mapper = new ObjectMapper();
	public List<Usuario> obtenerPacientes(){
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return mapper.readValue(response.body(), new TypeReference<List<Usuario>>() {});
		}catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	public void create(Usuario usuario) {
		try {
			String json = mapper.writeValueAsString(usuario);
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
	public Usuario verificarUsuario(String nombre, String contrasena) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/api/usuarios"))
					.GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			List<Usuario> usuarios = mapper.readValue(response.body(), new TypeReference<List<Usuario>>() {});

			return usuarios.stream()
					.filter(u -> u.getNombre().equals(nombre)
							&& u.getContraseña().equals(contrasena))
					.findFirst()
					.orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

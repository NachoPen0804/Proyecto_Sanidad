package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class ApiClientBase<T> {
	protected final HttpClient client = HttpClient.newHttpClient();
	protected final ObjectMapper mapper = new ObjectMapper();
	protected final String baseUrl;

	public ApiClientBase(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public CompletableFuture<List<T>> getAll() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl))
				.GET()
				.build();

		return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(response -> {
					try {
						return mapper.readValue(response.body(), new TypeReference<List<T>>() {
						});
					} catch (Exception e) {
						throw new RuntimeException("Error fetching data", e);
					}
				});
	}

	public CompletableFuture<T> create(T entity) {
		try {
			String json = mapper.writeValueAsString(entity);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(baseUrl))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();

			return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
					.thenApply(resp -> {
						try {
							return mapper.readValue(resp.body(), getTypeReference());
						} catch (Exception e) {
							throw new RuntimeException("Error creating entity", e);
						}
					});
		} catch (Exception e) {
			return CompletableFuture.failedFuture(e);
		}
	}

	// Implementa similares para update y delete
	protected abstract TypeReference<T> getTypeReference();
}

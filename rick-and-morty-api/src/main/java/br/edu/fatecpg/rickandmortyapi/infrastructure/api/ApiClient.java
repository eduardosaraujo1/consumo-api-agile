package br.edu.fatecpg.rickandmortyapi.infrastructure.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static HttpClient client = HttpClient.newHttpClient();

    /**
     * Envia uma requisição GET.
     * @param endpoint URL a enviar a requisição
     * @return Resposta no formato HttpResponse para representação de códigos de status
     * @throws InterruptedException
     * @throws IOException
     */
    public static HttpResponse<String> get(String endpoint)
        throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
            URI.create(endpoint)
        ).build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

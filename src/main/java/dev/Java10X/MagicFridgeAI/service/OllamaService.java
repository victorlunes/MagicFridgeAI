package dev.Java10X.MagicFridgeAI.service;

import dev.Java10X.MagicFridgeAI.DTO.OllamaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OllamaService {
    private final WebClient webClient;
    private final String model;

    public OllamaService(WebClient webClient, @Value("${ollama.model:llama3.2}") String model) {
        this.webClient = webClient;
        this.model = model;
    }

    public Mono<String> questAI(String texto) {
        if (texto == null || texto.isBlank()) {
            return Mono.error(new IllegalArgumentException("Prompt nao pode ser vazio."));
        }

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "prompt", texto.trim(),
                "stream", false
        );

        return webClient.post()
                //EndPoint
                .uri("/api/generate")
                //Estou enviando JSON no corpo da requisição.
                .contentType(MediaType.APPLICATION_JSON)
                //Quero receber JSON como resposta.
                .accept(MediaType.APPLICATION_JSON)
                //corpo da requisição,
                .bodyValue(requestBody)
                //Quero executar a chamada e recuperar a resposta HTTP.
                .retrieve()
                //Pegue o corpo da resposta JSON e converta para um Mono<OllamaResponseDTO>.
                .bodyToMono(OllamaResponseDTO.class)
                // Mono<OllamaResponseDTO> vai para Mono<String>
                .map(OllamaResponseDTO::response);
    }
}

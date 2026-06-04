package dev.Java10X.MagicFridgeAI.service.OllamaService;

import dev.Java10X.MagicFridgeAI.DTO.FoodItem.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.DTO.OllamaService.OllamaResponseDTO;
import dev.Java10X.MagicFridgeAI.service.FoodItem.FoodItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OllamaService {
    private final WebClient webClient;
    private final String model;
    private final FoodItemService foodItemService;

    public OllamaService(WebClient webClient, @Value("${ollama.model:llama3.2}") String model, FoodItemService foodItemService){
        this.webClient = webClient;
        this.model = model;
        this.foodItemService = foodItemService;
    }

    public Mono<String> questAI() {

        List<FoodItemDTO> allFoodItems = foodItemService.getFoodItem();

        if (allFoodItems.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Nenhum alimento cadastrado."));
        }

        String foodPrompt = """
        Você é um chefe de cozinha. Faça uma receita com os seguintes alimentos:
        %s
        """.formatted(
                allFoodItems.stream()
                        .map(food -> String.format(
                                "- %s, %s, %s",
                                food.getNome(),
                                food.getCategoria(),
                                food.getValidade()
                        ))
                        .collect(Collectors.joining("\n"))
        );

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "prompt", foodPrompt,
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

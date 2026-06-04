package dev.Java10X.MagicFridgeAI.controller.Recipe;

import dev.Java10X.MagicFridgeAI.service.OllamaService.OllamaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final OllamaService ollamaService;

    public RecipeController(OllamaService ollamaService){
        this.ollamaService = ollamaService;
    }

    @PostMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        return ollamaService.questAI()
                .map(ResponseEntity::ok)
                .onErrorResume(IllegalArgumentException.class, error -> Mono.just(
                        ResponseEntity.badRequest().body(error.getMessage())
                ))
                .onErrorResume(WebClientRequestException.class, error -> Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body("Nao foi possivel conectar ao Ollama: " + error.getMessage())
                ))
                .onErrorResume(WebClientResponseException.class, error -> Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body("Erro ao chamar o Ollama: " + error.getStatusCode() + " - " + error.getResponseBodyAsString())
                ));
    }
}

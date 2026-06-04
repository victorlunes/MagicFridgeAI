package dev.Java10X.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ollama.api.url}")
    private String ollamaUrlApi;

    @Bean
    public WebClient webClient (WebClient.Builder builder){
        return builder.baseUrl(ollamaUrlApi).build();
    }
    //Esse método cria um objeto WebClient e entrega para o Spring guardar.
    // como estamos passando o baseUrl aqui, quando injetar no service ele já vai vir
    // com o localhost do ollama, o .uri("/api/generate") é apenas o caminho final, ele vai jultar
    // as duas partes, tanto o que foi feito aqui, quanto o que esta no service

    //Já o WebClient é uma forma da gente fazer requisições http com o spring
    //No projeto, criamos o WebClientConfig para configurar um WebClient com a URL base do Ollama

    /*
    O WebFlux é um módulo reativo do Spring. Ele permite trabalhar com requisições e respostas
    de forma assíncrona e não bloqueante


    O Mono representa um fluxo reativo que pode emitir:
    0 ou 1 valor
    Mono = 0 ou 1 valor
    Flux = 0, 1 ou muitos valores
    */
}

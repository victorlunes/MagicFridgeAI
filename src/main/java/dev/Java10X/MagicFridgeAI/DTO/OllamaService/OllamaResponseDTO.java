package dev.Java10X.MagicFridgeAI.DTO.OllamaService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OllamaResponseDTO(String response) {
    //Como só queremos o response, no .bodyToMono(OllamaResponseDTO.class) ele tenta montar um
    //OllamaResponseDTO, como só tem o response ele só faz com o response
    // @JsonIgnoreProperties(ignoreUnknown = true) ignora os outros campos
}

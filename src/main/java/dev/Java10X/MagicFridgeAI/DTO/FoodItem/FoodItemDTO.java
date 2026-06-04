package dev.Java10X.MagicFridgeAI.DTO.FoodItem;

import dev.Java10X.MagicFridgeAI.enums.FoodItem.FoodItemEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private Long id;
    private String nome;
    private FoodItemEnum categoria;
    private Integer quantidade;
    private LocalDate validade;
}

package dev.Java10X.MagicFridgeAI.Mapper.FoodItem;

import dev.Java10X.MagicFridgeAI.DTO.FoodItem.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.model.FoodItem.FoodItemModel;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {
    public FoodItemModel mapToModel(FoodItemDTO foodItemDTO){
        FoodItemModel foodItemModel = new FoodItemModel();
        foodItemModel.setId(foodItemDTO.getId());
        foodItemModel.setNome(foodItemDTO.getNome());
        foodItemModel.setCategoria(foodItemDTO.getCategoria());
        foodItemModel.setQuantidade(foodItemDTO.getQuantidade());
        foodItemModel.setValidade(foodItemDTO.getValidade());
        return foodItemModel;
    }

    public FoodItemDTO mapToDto(FoodItemModel foodItemModel){
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItemModel.getId());
        foodItemDTO.setNome(foodItemModel.getNome());
        foodItemDTO.setCategoria(foodItemModel.getCategoria());
        foodItemDTO.setQuantidade(foodItemModel.getQuantidade());
        foodItemDTO.setValidade(foodItemModel.getValidade());
        return foodItemDTO;
    }
}

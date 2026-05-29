package dev.Java10X.MagicFridgeAI.service;

import dev.Java10X.MagicFridgeAI.DTO.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.FoodItemMapper.FoodItemMapper;
import dev.Java10X.MagicFridgeAI.model.FoodItemModel;
import dev.Java10X.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;

    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemMapper foodItemMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemMapper = foodItemMapper;
    }

    public List<FoodItemDTO> getFoodItem() {
        List<FoodItemModel> foodItems = foodItemRepository.findAll();

        return foodItems.stream().map(food -> foodItemMapper.mapToDto(food)).toList();
    }

    public FoodItemDTO getById(Long Id){
        Optional<FoodItemModel> foodItem = foodItemRepository.findById(Id);
        return foodItem.map(food -> foodItemMapper.mapToDto(food)).orElse(null);
    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemModel food = foodItemMapper.mapToModel(foodItemDTO);
        foodItemRepository.save(food);
        return foodItemMapper.mapToDto(food);
    }

    public FoodItemDTO updateFoodItem(Long id, FoodItemDTO newFoodItem){
        FoodItemModel foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found !"));

        foodItem.setNome(newFoodItem.getNome());
        foodItem.setValidade(newFoodItem.getValidade());
        foodItem.setCategoria(newFoodItem.getCategoria());
        foodItem.setQuantidade(newFoodItem.getQuantidade());

        FoodItemModel updatedFoodItem = foodItemRepository.save(foodItem);

        return foodItemMapper.mapToDto(updatedFoodItem);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}

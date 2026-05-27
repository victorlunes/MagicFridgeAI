package dev.Java10X.MagicFridgeAI.service;

import dev.Java10X.MagicFridgeAI.DTO.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.FoodItemMapper.FoodItemMapper;
import dev.Java10X.MagicFridgeAI.model.FoodItemModel;
import dev.Java10X.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemModel food = foodItemMapper.mapToModel(foodItemDTO);
        foodItemRepository.save(food);
        return foodItemMapper.mapToDto(food);
    }
}

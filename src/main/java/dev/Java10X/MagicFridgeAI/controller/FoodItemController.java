package dev.Java10X.MagicFridgeAI.controller;

import dev.Java10X.MagicFridgeAI.DTO.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.FoodItemMapper.FoodItemMapper;
import dev.Java10X.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService = foodItemService;
    }

    @GetMapping("/search-food")
    public ResponseEntity<List<FoodItemDTO>> getAllFood(){
        List<FoodItemDTO> foodItem = foodItemService.getFoodItem();
        return ResponseEntity.status(HttpStatus.OK).body(foodItem);
    }

    @PostMapping("/create-food")
    public ResponseEntity<FoodItemDTO> createFood(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodItem = foodItemService.createFoodItem(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItem);
    }

    //update

    //delete
}

package dev.Java10X.MagicFridgeAI.controller;

import dev.Java10X.MagicFridgeAI.DTO.FoodItemDTO;
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
    public ResponseEntity<List<FoodItemDTO>> getAllFood() {
        List<FoodItemDTO> foodItem = foodItemService.getFoodItem();
        if(foodItem.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(foodItem);
    }

    @GetMapping("/search-food/{id}")
    public ResponseEntity<FoodItemDTO> getFoodById(@PathVariable Long id) {
        FoodItemDTO food = foodItemService.getById(id);

        if(food == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @PostMapping("/create-food")
    public ResponseEntity<FoodItemDTO> createFood(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItem = foodItemService.createFoodItem(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItem);
    }

    @PutMapping("/update-food/{id}")
    public ResponseEntity<FoodItemDTO> updateFood(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItem = foodItemService.updateFoodItem(id, foodItemDTO);
        if(foodItem == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(foodItem);
    }

    @DeleteMapping("/delete-food/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}

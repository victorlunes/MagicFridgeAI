package dev.Java10X.MagicFridgeAI.controller;

import dev.Java10X.MagicFridgeAI.DTO.FoodItemDTO;
import dev.Java10X.MagicFridgeAI.FoodItemMapper.FoodItemMapper;
import dev.Java10X.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private FoodItemService foodItemService;
    private FoodItemMapper foodItemMapper;

    public FoodItemController(FoodItemService foodItemService, FoodItemMapper foodItemMapper){
        this.foodItemService = foodItemService;
        this.foodItemMapper = foodItemMapper;
    }

    //get
    @GetMapping
    public ResponseEntity<List<FoodItemDTO>> getAllFood(){
        List<FoodItemDTO> foodItem = foodItemService.getFoodItem();
        return ResponseEntity.status(HttpStatus.OK).body(foodItem);
    }

    //post

    //update

    //delete
}

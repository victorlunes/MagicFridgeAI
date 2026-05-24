package dev.Java10X.MagicFridgeAI.controller;

import dev.Java10X.MagicFridgeAI.service.FoodItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService = foodItemService;
    }

    //get

    //post

    //update

    //delete
}

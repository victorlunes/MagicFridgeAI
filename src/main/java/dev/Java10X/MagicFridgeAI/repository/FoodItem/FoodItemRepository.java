package dev.Java10X.MagicFridgeAI.repository.FoodItem;

import dev.Java10X.MagicFridgeAI.model.FoodItem.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
}

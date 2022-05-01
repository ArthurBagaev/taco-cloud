package com.github.moreart.tacocloud.repositories;

import com.github.moreart.tacocloud.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}

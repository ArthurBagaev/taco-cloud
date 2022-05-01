package com.github.moreart.tacocloud.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.github.moreart.tacocloud.models.Ingredient.Type;
import com.github.moreart.tacocloud.models.Ingredient;
import com.github.moreart.tacocloud.models.Order;
import com.github.moreart.tacocloud.models.Taco;
import com.github.moreart.tacocloud.repositories.IngredientRepository;
import com.github.moreart.tacocloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {


    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }


    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type [] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream()
                                .filter(i -> i.getType().name().equals(type.name()))
                                .collect(Collectors.toList()));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

}

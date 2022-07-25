package com.eukolos.taco.Controller;


import com.eukolos.taco.Model.TacoModel;
import com.eukolos.taco.Model.TacoModel.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        List<TacoModel> ingredients = Arrays.asList(
                new TacoModel("FLTO", "Flour Tortilla", Type.WRAP),
                new TacoModel("COTO", "Corn Tortilla", Type.WRAP),
                new TacoModel("GRBF", "Ground Beef", Type.PROTEIN),
                new TacoModel("CARN", "Carnitas", Type.PROTEIN),
                new TacoModel("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new TacoModel("LETC", "Lettuce", Type.VEGGIES),
                new TacoModel("CHED", "Cheddar", Type.CHEESE),
                new TacoModel("JACK", "Monterrey Jack", Type.CHEESE),
                new TacoModel("SLSA", "Salsa", Type.SAUCE),
                new TacoModel("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = TacoModel.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }

        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }
    private List<TacoModel> filterByType(List<TacoModel> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }
}


package com.company.web.springdemo.controllers.mvc;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.FilterOptions;
import com.company.web.springdemo.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/beers")
public class BeerMvcController {
    private final BeerService beerService;

    @Autowired
    public BeerMvcController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model){
        try {
            Beer beer = beerService.get(id);
            model.addAttribute("beer", beer);
            return "beer";
        }catch (EntityNotFoundException e){
            model.addAttribute("error", e.getMessage());
            return "not-found";
        }
    }

    @GetMapping()
    public String showAllBeers(Model model, @RequestParam(required = false) String name,
                               @RequestParam(required = false) Double minAbv,
                               @RequestParam(required = false) Double maxAbv,
                               @RequestParam(required = false) Integer styleId,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String sortOrder){
        FilterOptions filterOptions = new FilterOptions(name, minAbv, maxAbv, styleId, sortBy, sortOrder);
        List<Beer> beers = beerService.get(filterOptions);
        model.addAttribute("beers", beers);
        return "beers";
    }
}

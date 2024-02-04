package com.company.web.springdemo.controllers.mvc;

import com.company.web.springdemo.exceptions.EntityDuplicateException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.helpers.BeerMapper;
import com.company.web.springdemo.models.*;
import com.company.web.springdemo.services.BeerService;
import com.company.web.springdemo.services.StyleService;
import com.company.web.springdemo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/beers")
public class BeerMvcController {

    private final BeerService beerService;
    private final UserService userService;
    private final StyleService styleService;
    private final BeerMapper beerMapper;

    @Autowired
    public BeerMvcController(BeerService beerService,
                             BeerMapper beerMapper,
                             UserService userService,
                             StyleService styleService) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
        this.userService = userService;
        this.styleService = styleService;
    }

    @ModelAttribute("styles")
    public List<Style> populateStyles() {
        return styleService.get();
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @GetMapping
    public String showAllBeers(Model model) {
        model.addAttribute("beers", beerService.get(new FilterOptions()));
        return "BeersView";
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model) {
        try {
            Beer beer = beerService.get(id);
            model.addAttribute("beer", beer);
            return "BeerView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @GetMapping("/new")
    public String showNewBeerPage(Model model) {
        model.addAttribute("beer", new BeerDto());
        return "BeerCreateView";
    }

    @PostMapping("/new")
    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beerDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "BeerCreateView";
        }

        try {
            //ToDo Rework with current user in MVC authentication session.
            User user = userService.get(1);
            Beer beer = beerMapper.fromDto(beerDto);
            beerService.create(beer, user);
            return "redirect:/beers";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_beer", e.getMessage());
            return "BeerCreateView";
        }
    }

    @GetMapping("/{id}/update")
    public String showEditBeerPage(@PathVariable int id, Model model) {
        try {
            Beer beer = beerService.get(id);
            BeerDto beerDto = beerMapper.toDto(beer);
            model.addAttribute("beerId", id);
            model.addAttribute("beer", beerDto);
            return "BeerUpdateView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/{id}/update")
    public String updateBeer(@PathVariable int id,
                             @Valid @ModelAttribute("beer") BeerDto dto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "BeerUpdateView";
        }

        try {
            //ToDo Rework with current user in MVC authentication session.
            User user = userService.get(1);
            Beer beer = beerMapper.fromDto(id, dto);
            beerService.update(beer, user);
            return "redirect:/beers";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_beer", e.getMessage());
            return "BeerUpdateView";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteBeer(@PathVariable int id, Model model) {
        try {
            //ToDo Rework with current user in MVC authentication session.
            User user = userService.get(1);
            beerService.delete(id, user);
            return "redirect:/beers";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

}

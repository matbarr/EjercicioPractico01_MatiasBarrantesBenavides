package com.GymFitness.MatiasBarrantes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.GymFitness.MatiasBarrantes.domain.ContactoForm;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @GetMapping("/contacto")
    public String mostrarContacto(Model model) {
        model.addAttribute("contactoForm", new ContactoForm());
        return "contacto";
    }

    @PostMapping("/contacto")
    public String procesarContacto(@Valid @ModelAttribute("contactoForm") ContactoForm contactoForm,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "contacto";
        }

        model.addAttribute("mensajeExito", "Gracias por tu mensaje. Pronto te contactaremos.");
        model.addAttribute("contactoForm", new ContactoForm());
        return "contacto";
    }
}

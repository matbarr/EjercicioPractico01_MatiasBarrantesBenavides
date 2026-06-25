package com.GymFitness.MatiasBarrantes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GymFitness.MatiasBarrantes.domain.Categoria;
import com.GymFitness.MatiasBarrantes.domain.Servicio;
import com.GymFitness.MatiasBarrantes.service.CategoriaService;
import com.GymFitness.MatiasBarrantes.service.ServicioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final CategoriaService categoriaService;

    public ServicioController(ServicioService servicioService, CategoriaService categoriaService) {
        this.servicioService = servicioService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioService.listarServicios());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Servicio servicio = new Servicio();
        servicio.setCategoria(new Categoria());
        model.addAttribute("servicio", servicio);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("servicio") Servicio servicio,
                          BindingResult bindingResult,
                          Model model) {
        if (servicio.getCategoria() == null || servicio.getCategoria().getId() == null) {
            bindingResult.rejectValue("categoria.id", "categoria.id", "Debe seleccionar una categoria");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "servicios/formulario";
        }

        servicio.setCategoria(categoriaService.obtenerCategoriaPorId(servicio.getCategoria().getId()));
        servicioService.guardarServicio(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("servicio", servicioService.obtenerServicioPorId(id));
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "servicios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        servicioService.eliminarServicio(id);
        return "redirect:/servicios";
    }
}

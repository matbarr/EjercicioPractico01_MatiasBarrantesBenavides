package com.GymFitness.MatiasBarrantes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GymFitness.MatiasBarrantes.domain.Reserva;
import com.GymFitness.MatiasBarrantes.domain.Servicio;
import com.GymFitness.MatiasBarrantes.service.ReservaService;
import com.GymFitness.MatiasBarrantes.service.ServicioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ServicioService servicioService;

    public ReservaController(ReservaService reservaService, ServicioService servicioService) {
        this.reservaService = reservaService;
        this.servicioService = servicioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarReservas());
        return "reservas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Reserva reserva = new Reserva();
        reserva.setServicio(new Servicio());
        model.addAttribute("reserva", reserva);
        model.addAttribute("servicios", servicioService.listarServicios());
        return "reservas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("reserva") Reserva reserva,
                          BindingResult bindingResult,
                          Model model) {
        if (reserva.getServicio() == null || reserva.getServicio().getId() == null) {
            bindingResult.rejectValue("servicio.id", "servicio.id", "Debe seleccionar una clase o servicio");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("servicios", servicioService.listarServicios());
            return "reservas/formulario";
        }

        reserva.setServicio(servicioService.obtenerServicioPorId(reserva.getServicio().getId()));
        reservaService.guardarReserva(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("reserva", reservaService.obtenerReservaPorId(id));
        model.addAttribute("servicios", servicioService.listarServicios());
        return "reservas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        reservaService.eliminarReserva(id);
        return "redirect:/reservas";
    }
}

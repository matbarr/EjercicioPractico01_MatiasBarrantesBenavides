package com.GymFitness.MatiasBarrantes.service;

import java.util.List;

import com.GymFitness.MatiasBarrantes.domain.Servicio;

public interface ServicioService {

    List<Servicio> listarServicios();

    Servicio obtenerServicioPorId(Integer id);

    Servicio guardarServicio(Servicio servicio);

    void eliminarServicio(Integer id);
}

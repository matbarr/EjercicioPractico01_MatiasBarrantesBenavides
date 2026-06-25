package com.GymFitness.MatiasBarrantes.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.GymFitness.MatiasBarrantes.domain.Servicio;
import com.GymFitness.MatiasBarrantes.repository.ServicioRepository;
import com.GymFitness.MatiasBarrantes.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio obtenerServicioPorId(Integer id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con id: " + id));
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Integer id) {
        servicioRepository.deleteById(id);
    }
}

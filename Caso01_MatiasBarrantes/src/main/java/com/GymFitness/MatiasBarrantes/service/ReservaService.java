package com.GymFitness.MatiasBarrantes.service;

import java.util.List;

import com.GymFitness.MatiasBarrantes.domain.Reserva;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva obtenerReservaPorId(Integer id);

    Reserva guardarReserva(Reserva reserva);

    void eliminarReserva(Integer id);
}

package com.GymFitness.MatiasBarrantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymFitness.MatiasBarrantes.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}

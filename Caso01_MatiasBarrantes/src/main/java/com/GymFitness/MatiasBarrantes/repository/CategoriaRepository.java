package com.GymFitness.MatiasBarrantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GymFitness.MatiasBarrantes.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

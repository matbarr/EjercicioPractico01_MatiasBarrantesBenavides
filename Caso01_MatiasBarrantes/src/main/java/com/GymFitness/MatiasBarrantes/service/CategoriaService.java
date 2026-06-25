package com.GymFitness.MatiasBarrantes.service;

import java.util.List;

import com.GymFitness.MatiasBarrantes.domain.Categoria;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoriaPorId(Integer id);

    Categoria guardarCategoria(Categoria categoria);

    void eliminarCategoria(Integer id);
}

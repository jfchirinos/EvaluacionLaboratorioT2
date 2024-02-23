package com.cine.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.demo.model.Pelicula;
import com.cine.demo.repository.PeliculaRepository;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public List<Pelicula> getAllPeliculas() {
		return peliculaRepository.findAll();
	}
	
	public void registrarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
    }
	
	public Pelicula getPeliculaById(Long id) {
		return peliculaRepository.findById(id).orElse(null);
	}
	
	public void eliminarPelicula(Long id) {
		peliculaRepository.deleteById(id);
	}	
}

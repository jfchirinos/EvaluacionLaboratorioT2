package com.cine.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.demo.model.Genero;
import com.cine.demo.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> getAllGeneros(){
		return generoRepository.findAll();
	}
	public Genero getGeneroById(Long id) {
        Optional<Genero> generoOptional = generoRepository.findById(id);
        return generoOptional.orElse(null);
    }
	
}

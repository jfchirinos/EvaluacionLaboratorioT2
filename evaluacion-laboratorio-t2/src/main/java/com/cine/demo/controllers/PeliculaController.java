package com.cine.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cine.demo.model.Genero;
import com.cine.demo.model.Pelicula;
import com.cine.demo.service.GeneroService;
import com.cine.demo.service.PeliculaService;

@Controller
@RequestMapping("/pelicula")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/peliculas")
	public String getAllPeliculas(Model model) {
        List<Pelicula> lisPeliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas", lisPeliculas); 
        return "peliculaList";  
    }
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "peliculaRegister";
	}
		
	@PostMapping("/register")
	public String registerPelicula(@RequestParam("nombre") String nombre,
	                                @RequestParam("director") String director,
	                                @RequestParam("fechaestreno") String fechaestrenoStr,
	                                @RequestParam("idgenero") Long idGenero, 
	                                Model model) {
		
	    Genero genero = generoService.getGeneroById(idGenero);
	    
	    Pelicula pelicula = new Pelicula();
	    pelicula.setNombre(nombre);
	    pelicula.setDirector(director);
	    try {
	        Date fechaestreno = new SimpleDateFormat("yyyy-MM-dd").parse(fechaestrenoStr);
	        pelicula.setFechaestreno(fechaestreno);
	    } catch (ParseException e) {
	    	System.out.println(e);
	    }
	    pelicula.setGenero(genero);

	    peliculaService.registrarPelicula(pelicula);
	    
	    List<Pelicula> listPeliculas = peliculaService.getAllPeliculas();
		model.addAttribute("peliculas", listPeliculas);

	    return "peliculaList"; 
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
	    Pelicula pelicula = peliculaService.getPeliculaById(id);
	    model.addAttribute("pelicula", pelicula);
	    model.addAttribute("generos", generoService.getAllGeneros());
	    return "peliculaEdit";
	}
		
	@PostMapping("/edit")
	public String editPelicula(@RequestParam("id") Long id,
	                            @RequestParam("nombre") String nombre,
	                            @RequestParam("director") String director,
	                            @RequestParam("fechaestreno") String fechaestrenoStr,
	                            @RequestParam("idgenero") Long idGenero,
	                            Model model) {

	    Genero genero = generoService.getGeneroById(idGenero);

	    Pelicula pelicula = peliculaService.getPeliculaById(id);
	    pelicula.setNombre(nombre);
	    pelicula.setDirector(director);
	    try {
	        Date fechaestreno = new SimpleDateFormat("yyyy-MM-dd").parse(fechaestrenoStr);
	        pelicula.setFechaestreno(fechaestreno);
	    } catch (ParseException e) {
	        System.out.println(e);
	    }
	    pelicula.setGenero(genero);

	    peliculaService.registrarPelicula(pelicula);

	    List<Pelicula> listPeliculas = peliculaService.getAllPeliculas();
	    model.addAttribute("peliculas", listPeliculas);

	    return "peliculaList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		peliculaService.eliminarPelicula(id);
		
		List<Pelicula> listPeliculas = peliculaService.getAllPeliculas();
	    model.addAttribute("peliculas", listPeliculas);
		
		return "peliculaList";
	}
}

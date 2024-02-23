package com.cine.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpelicula", nullable = false)
	public Long idpelicula;

    @Column(name = "nombre", nullable = false)
    public String nombre;

    @Column(name = "director", nullable = false)
    public String director;

    @Column(name = "fechaestreno", nullable = false)
    public Date fechaestreno;

    @ManyToOne
    @JoinColumn(name = "idgenero", nullable = false)
    public Genero genero;
    
    public Pelicula() {
    }

    public Pelicula(String nombre, String director
    		, Date fechaestreno, 
    		Genero genero
    		) {
        this.nombre = nombre;
        this.director = director;
        this.fechaestreno = fechaestreno;
        this.genero = genero;
    }

    public Long getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(Long idpelicula) {
        this.idpelicula = idpelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getFechaestreno() {
        return fechaestreno;
    }

    public void setFechaestreno(Date fechaestreno) {
        this.fechaestreno = fechaestreno;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
	
}

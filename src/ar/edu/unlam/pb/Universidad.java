package ar.edu.unlam.pb;

import java.util.HashSet;
import java.util.Set;

public class Universidad {

	private String nombreUniversidad;
	private Set<Alumno> alumnos;
	
	
	public Universidad(String nombreUniversidad) {
		// TODO Auto-generated constructor stub
		this.nombreUniversidad = nombreUniversidad;
		this.alumnos = new HashSet<>();
	}
	public String getNombreUniversidad() {
		return nombreUniversidad;
	}
	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
	}
	public boolean agregarAlumno(Alumno alumno) {
        // Verificar si ya existe un alumno con el mismo ID
        for (Alumno alumnoExistente : alumnos) {
            if (alumnoExistente.getDni().equals(alumno.getDni())) {
                return false; // Ya existe un alumno con el mismo ID, no se puede agregar.
            }
        }
        
        // Si no existe, agregar el alumno y retornar true
        alumnos.add(alumno);
        return true;
    }
	
	

}

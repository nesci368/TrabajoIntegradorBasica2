package ar.edu.unlam.pb;

import java.util.HashSet;
import java.util.Set;

public class Universidad {

	private String nombreUniversidad;
	private Set<Alumno> alumnos;
	private Set<Materia> materias;
	
	
	public Universidad(String nombreUniversidad) {
		// TODO Auto-generated constructor stub
		this.nombreUniversidad = nombreUniversidad;
		this.alumnos = new HashSet<>();
		this.materias = new HashSet<>();
	}
	public String getNombreUniversidad() {
		return nombreUniversidad;
	}
	public void setNombreUniversidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
	}
	public boolean agregarAlumno(Alumno alumno) {
        for (Alumno alumnoExistente : alumnos) {
            if (alumnoExistente.getDni().equals(alumno.getDni())) {
                return false;
            }
        }
        alumnos.add(alumno);
        return true;
    }
	
	public boolean agregarMateria(Materia materia) {
        if (materias == null) {
            materias = new HashSet<>();
        }
        
        for (Materia materiaExistente : materias) {
            if (materiaExistente.getCodigoMateria().equals(materia.getCodigoMateria())) {
                return false;
            }
        }
        materias.add(materia);
        return true;
    }
	
	
	

}

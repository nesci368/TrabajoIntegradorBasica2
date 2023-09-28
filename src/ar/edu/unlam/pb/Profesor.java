package ar.edu.unlam.pb;

import java.time.LocalDate;
import java.util.ArrayList;

public class Profesor {
    
	private String nombre;
	private String apellido;
	private Integer profesorDni;
	private ArrayList<Comision> comisiones;

    Profesor(Integer profesorDni, String nombre, String apellido) {
    	this.nombre = nombre;
    	this.apellido = apellido;
        this.profesorDni = profesorDni;
        this.comisiones = new ArrayList<>();
    }

	public Integer getProfesorId() {
		return profesorDni;
	}

	public void setProfesorId(Integer profesorId) {
		this.profesorDni = profesorId;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ArrayList<Comision> getComisiones() {
		return comisiones;
	}

	public void setComisiones(ArrayList<Comision> comisiones) {
		this.comisiones = comisiones;
	}

	public boolean cargarNotaAAlumno(Alumno alumno, Materia materia, String tipoNota, Integer nota, LocalDate fecha) {
	    Comision comision = encontrarComisionPorMateria(materia);

	    if (comision != null) {
	        if (comision.inscribirAlumno(alumno, nota, fecha)) {
	            return comision.registrarNotaParcial(alumno, tipoNota, nota);
	        }
	    }
	    return false;
	}
	
	public Comision encontrarComisionPorMateria(Materia materia) {
	    for (Comision comision : comisiones) {
	        if (comision.getMateria().equals(materia)) {
	            return comision; 
	        }
	    }
	    return null;
	}

    
}

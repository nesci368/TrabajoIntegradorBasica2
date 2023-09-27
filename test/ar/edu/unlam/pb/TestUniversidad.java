package ar.edu.unlam.pb;

import static org.junit.Assert.*; 

import org.junit.Test;


public class TestUniversidad {

	@Test
	public void queSePuedaCrearUnaUniversidad(){
		String nombreUniversidad = "UNLaM";
		Universidad universidad = new Universidad(nombreUniversidad);
		assertNotNull(universidad);
	}
	
	@Test
	public void testAgregarAlumnos() {
	    Universidad universidad = new Universidad("UNLaM"); // Crear una universidad

	    
	    for (int i = 1; i <= 40; i++) {
	        Alumno alumno = new Alumno("Leandro", "Nesci", 36954744 + i);
	        assertTrue(universidad.agregarAlumno(alumno));
	    }
	}
	
	@Test
	public void testNoAgregarAlumnoRepetido() {
	    Universidad universidad = new Universidad("UNLaM"); // Crear una universidad
	    Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);
	    assertTrue(universidad.agregarAlumno(alumno1));

	    Alumno alumno2 = new Alumno("Leandro", "Nesci", 36954744);
	    assertFalse(universidad.agregarAlumno(alumno2));
	}

}

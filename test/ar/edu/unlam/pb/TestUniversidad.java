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

}

package ar.edu.unlam.pb;

import static org.junit.Assert.*; 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class TestUniversidad {

	@Test
	public void queSePuedaCrearUnaUniversidad() {
		String nombreUniversidad = "UNLaM";
		Universidad universidad = new Universidad(nombreUniversidad);
		assertNotNull(universidad);
	}

	@Test
	public void testAgregarAlumnos() {
		Universidad universidad = new Universidad("UNLaM");

		for (int i = 1; i <= 40; i++) {
			Alumno alumno = new Alumno("Leandro", "Nesci", 36954744 + i);
			assertTrue(universidad.agregarAlumno(alumno));
		}
	}

	@Test
	public void testNoAgregarAlumnoRepetido() {
		Universidad universidad = new Universidad("UNLaM");
		Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);
		assertTrue(universidad.agregarAlumno(alumno1));

		Alumno alumno2 = new Alumno("Leandro", "Nesci", 36954744);
		assertFalse(universidad.agregarAlumno(alumno2));
	}

	@Test
	public void testAgregarMateria() {
		Universidad universidad = new Universidad("UNLaM");

		for (int i = 1; i <= 20; i++) {
			Materia materia = new Materia("Programacion Basica 1", "2619" + i, 6.0);
			assertTrue(universidad.agregarMateria(materia));
		}
	}

	@Test
	public void testNoAgregarMateriaRepetida() {
		Universidad universidad = new Universidad("UNLaM");
		Materia materia1 = new Materia("Programacion Basica 1", "2619", 7.0);
		assertTrue(universidad.agregarMateria(materia1));

		Materia materia2 = new Materia("Programacion Basica 1", "2619", 7.0);
		assertFalse(universidad.agregarMateria(materia2));
	}
	
	@Test
	public void queSePuedaAsociarUnaMateriaCorrelativaAOtraMateria() {
		String  nombre = "Unlam";
    	Universidad unlam = new Universidad (nombre);
    	
    	nombre = "PB1";
        Integer codigo = 1;
		Materia materia1 = new Materia("Programacion Basica 1", "2619", 7.0);

		Materia materia2 = new Materia("Programacion Basica 2", "2623", 8.0);
        unlam.registrarMateria(materia1);
        
        nombre = "PB2";
        codigo = 2;
        ArrayList<Materia> correlativas = new ArrayList<>();
        correlativas.add(materia2);
		

        
        materia2.agregarCorrelativa(materia1);
        unlam.registrarMateria(materia2);
        
        ArrayList<Materia> correlativas2 = (ArrayList<Materia>) materia2.getCorrelativas();
        
        assertTrue(correlativas2.contains(materia1));
	}
	
	@Test
	public void queNoSePuedaAsociarUnaMateriaCorrelativaAOtraMateriaPorqueYaExiste() {
		String  nombre = "Unlam";
    	Universidad unlam = new Universidad (nombre);
    	
    	nombre = "PB1";
        Integer codigo = 1;
		Materia materia1 = new Materia("Programacion Basica 1", "2619", 7.0);

        
        unlam.registrarMateria(materia1);
        
        nombre = "PB2";
        codigo = 2;
        ArrayList<Materia> correlativas = new ArrayList<>();
        
		Materia materia2 = new Materia("Programacion Basica 2", "2623", 8.0);
		correlativas.add(materia2);
        
		materia2.agregarCorrelativa(materia1);
        assertFalse(materia2.agregarCorrelativa(materia1));
        
	}

	@Test
	public void testAgregarComision() {
		Universidad universidad = new Universidad("UNLaM");

		Materia materia1 = new Materia("Programacion Basica 1", "2619", 7.0);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;

		Comision comision1 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia1, dia, horario,
				notaAprobacion, capacidad);

		assertTrue(universidad.agregarComision(comision1));
	}

	@Test
	public void testNoAgregarComisionParaLaMismaMateria() {
		Universidad universidad = new Universidad("UNLaM");
		Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;

		Comision comision1 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertTrue(universidad.agregarComision(comision1));

		Comision comision2 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertFalse(universidad.agregarComision(comision2));
	}

	@Test
	public void testNoAgregarComisionConMismoIdentificador() {
		Universidad universidad = new Universidad("UNLaM"); 
		Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;

		Comision comision1 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertTrue(universidad.agregarComision(comision1));

		Comision comision2 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertFalse(universidad.agregarComision(comision2));
	}

	@Test
	public void testAgregarDosDocentesAUnaComisionSinOtraComisionEnLaMismaFecha() {
		Universidad universidad = new Universidad("UNLaM"); 
		Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;

		Comision comision1 = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertTrue(universidad.agregarComision(comision1));

		Comision comision2 = new Comision("C002", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertFalse(universidad.agregarComision(comision2));
	}

	@Test
	public void testCrearAulaSinRepetirNumeroDeAula() {
		Universidad universidad = new Universidad("UNLaM"); 
		Aula aula1 = new Aula("Lab4");
		assertTrue(universidad.agregarAula(aula1));

		Aula aula2 = new Aula("Lab4");
		assertFalse(universidad.agregarAula(aula2));
	}

	@Test
	public void testNoCrearCicloLectivoConLaMismaFecha() {
		Universidad universidad = new Universidad("UNLaM"); 
		LocalDate fechaInicio = LocalDate.of(2023, 4, 4);
		LocalDate fechaFin = LocalDate.of(2023, 7, 31);
		String cuatrimestreActual = "1C";

		CicloLectivo cicloLectivo = new CicloLectivo(fechaInicio, fechaFin, cuatrimestreActual);
		assertTrue(universidad.agregarCicloLectivo(cicloLectivo));

		CicloLectivo cicloLectivo1 = new CicloLectivo(fechaInicio, fechaFin, cuatrimestreActual);
		assertFalse(universidad.agregarCicloLectivo(cicloLectivo1));
	}

	@Test
	public void testInscribirAlumnoALaComision() {
		Universidad universidad = new Universidad("UNLaM"); 
		Alumno alumno = new Alumno("Leandro", "Nesci", 36954744);
		assertTrue(universidad.agregarAlumno(alumno));

		Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
		assertTrue(universidad.agregarMateria(materia));

		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;

		Comision comision = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				capacidad, notaAprobacion);
		assertTrue(universidad.agregarComision(comision));
		boolean inscripcionExitosa = comision.inscribirAlumno(alumno, notaAprobacion, LocalDate.of(2023, 04, 01));

	
		assertTrue(inscripcionExitosa);

		
		assertTrue(comision.getAlumnosInscriptos().contains(alumno));

		
		assertTrue(alumno.getComisionesInscriptas().contains(comision));
	}

	@Test
	public void testNoInscribirAlumnoEnComisionLlena() {
		Universidad universidad = new Universidad("UNLaM"); // Crear una universidad
		Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);

		assertTrue(universidad.agregarAlumno(alumno1));

		Alumno alumno2 = new Alumno("Vanesa", "Hermosilla", 34571945);

		assertTrue(universidad.agregarAlumno(alumno2));

		Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
		assertTrue(universidad.agregarMateria(materia));

		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 1;

		Comision comision = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
				notaAprobacion, capacidad);
		assertTrue(universidad.agregarComision(comision));

		assertTrue(comision.inscribirAlumno(alumno1, notaAprobacion, LocalDate.of(2023, 04, 01)));
		assertFalse(universidad.inscribirAlumnoALaComision(alumno2, comision.getId(), LocalDate.now(), 7));
	}

	@Test
	public void testAprobarMateria() {
	    Universidad universidad = new Universidad("UNLaM");
	    Alumno alumno = new Alumno("Leandro", "Nesci", 36954744);
	    assertTrue(universidad.agregarAlumno(alumno));

	    Materia materia = new Materia("Programacion Basica 1", "2619", 7.0);
	    assertTrue(universidad.agregarMateria(materia));

	    LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
	    LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
	    String dia = "Lunes";
	    String horario = "19:00hs";
	    Integer notaAprobacion = 5;
	    Integer capacidad = 40;

	    Comision comision = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia, dia, horario,
	            notaAprobacion, capacidad);
	    assertTrue(universidad.agregarComision(comision));

	    assertTrue(universidad.inscribirAlumnoALaComision(alumno, comision.getId(), LocalDate.now(), 7));

	    Materia materiaAprobada = new Materia("Programacion Basica 1", "2619", 7.0);

	    alumno.aprobarMateria(materiaAprobada);

	    assertTrue(universidad.tieneMateriasAprobadas(materiaAprobada, comision));
	}
	
	
	@Test
    public void testObtenerListadoMateriasAprobadasParaUnAlumno() {
		 Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);
		 Alumno alumno2= new Alumno("Vanesa", "Hermosilla", 34571945);
		 Universidad universidad = new Universidad("UNLaM");
		 
        List<String> listado = universidad.obtenerListadoMateriasAprobadasParaUnAlumno(222);
        assertEquals(1, listado.size());
        assertTrue(listado.get(0).contains("222 Juan Perez PB2 7.0"));

        listado = universidad.obtenerListadoMateriasAprobadasParaUnAlumno(444);
        assertEquals(1, listado.size());
        assertTrue(listado.get(0).contains("444 Lucia Garcia PB2 7.0"));
    }

    @Test
    public void testObtenerMateriasQueFaltanCursarParaUnAlumno() {
    	
    	Universidad universidad = new Universidad("UNLaM");
    	Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);
    	 Materia materia1 = new Materia("Programacion Basica 2", "2623", 7.0);
        alumno1.aprobarMateria(materia1);
        
        List<String> materiasFaltantes = universidad.obtenerMateriasQueFaltanCursarParaUnAlumno(222);
        assertEquals(1, materiasFaltantes.size());
        assertTrue(materiasFaltantes.contains("PB1"));
        
        materiasFaltantes = universidad.obtenerMateriasQueFaltanCursarParaUnAlumno(444);
        assertEquals(2, materiasFaltantes.size());
        assertTrue(materiasFaltantes.contains("PB1"));
        assertTrue(materiasFaltantes.contains("PB2"));
    }

    @Test
    public void testObtenerReporteDeNotasDeAlumnosDeCurso() {
    	
    	LocalDate fechaInicioInscripcion = LocalDate.of(2023, 03, 31);
		LocalDate fechaFinalInscripcion = LocalDate.of(2023, 04, 10);
		String dia = "Lunes";
		String horario = "19:00hs";
		Integer notaAprobacion = 5;
		Integer capacidad = 40;
		String tipoNota = "Final";
    	
    	
    	Universidad universidad = new Universidad("UNLaM");
        Curso curso = new Curso(null, "Curso 1");
    	Alumno alumno1 = new Alumno("Leandro", "Nesci", 36954744);
		Alumno alumno2= new Alumno("Vanesa", "Hermosilla", 34571945);
   	 	Materia materia1 = new Materia("Programacion Basica 1", "2619", 7.0);
   	 	Materia materia2 = new Materia("Programacion Basica 2", "2623", 7.0);
   	 	Comision comision = new Comision("C001", fechaInicioInscripcion, fechaFinalInscripcion, materia1, dia, horario,
				capacidad, notaAprobacion);

        curso.agregarAlumno(alumno1);
        curso.agregarAlumno(alumno2);
        curso.agregarMateria(materia1);
        curso.agregarMateria(materia2);

        alumno1.registrarNotaFinal(comision,7, tipoNota);
        alumno2.registrarNotaFinal(comision, 7, tipoNota);

        List<String> reporte = universidad.obtenerReporteDeNotasDeAlumnosDeCurso(1);
        assertEquals(2, reporte.size());
        assertTrue(reporte.get(0).contains("1 PB2 222 Juan Perez 7.0"));
        assertTrue(reporte.get(1).contains("1 PB2 444 Lucia Garcia 7.0"));
    }


}

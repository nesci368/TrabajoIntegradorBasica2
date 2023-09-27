package ar.edu.unlam.pb;

import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Comision {
	private String id;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechaFinInscripcion;
	private Materia materia;
	private String dia;
	private String horario;
	private List<Alumno> alumnosInscritos = new ArrayList<>();
	private Integer capacidad;
	private Integer notaAprobacion;
	private Set<Materia> materias;
	private Set<Profesor> docentes;
	private Map<Alumno, Map<String, Nota>> notasPorAlumno; // Mapa para registrar notas de alumnos

	public Comision(String id, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion, Materia materia,
			String dia, String horario, Integer capacidad, Integer notaAprobacion) {
		this.id = id;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
		this.materia = materia;
		this.dia = dia;
		this.horario = horario;
		this.capacidad = capacidad;
		this.notaAprobacion = notaAprobacion;
		this.notasPorAlumno = new HashMap<>();
	}

	public Comision(String id2, LocalDate of, LocalDate of2, String string, String dia2, String horario2,
			int capacidad2, int notaAprobacion2) {

	}

	public String getCodigoComision() {
		return id;
	}

	public void setCodigoComision(String codigoComision) {
		this.id = codigoComision;
	}

	public Set<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

	public Set<Profesor> getDocentes() {
		return docentes;
	}

	public void setDocentes(Set<Profesor> docentes) {
		this.docentes = docentes;
	}

	public boolean asignarDocente(Profesor docente) {
		if (docentes.contains(docente)) {
			return false;
		}
		docentes.add(docente);
		return true;
	}

	public String getId() {
		return id;
	}

	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	public LocalDate getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}

	public Materia getMateria() {
		return materia;
	}

	public String getDia() {
		return dia;
	}

	public String getHorario() {
		return horario;
	}

	public boolean tieneCapacidadDisponible() {
		return alumnosInscritos.size() < capacidad;
	}

	public int getNotaAprobacion() {
		return notaAprobacion;
	}

	public boolean inscribirAlumno(Alumno alumno, Integer notaAprobacion, LocalDate fechaInscripcion) {
		if (alumnosInscritos.size() < capacidad && notaAprobacion >= this.notaAprobacion
				&& fechaInscripcion.isAfter(fechaInicioInscripcion) && fechaInscripcion.isBefore(fechaFinInscripcion)
				&& !alumno.estaInscritoEnOtraComisionEnElMismoHorario(dia, horario)
				&& alumno.tieneCorrelativasAprobadas(materia) && !alumno.tieneMateriasAprobadas(materia)) {

			alumnosInscritos.add(alumno);
			alumno.inscribirComision(this);
			return true;
		}
		return false;
	}

	public boolean asignarDocentesALaComision(List<Profesor> docentesDisponibles) {
		if (docentesDisponibles.isEmpty() || alumnosInscritos.isEmpty()) {
			return false;
		}

		int cantidadAlumnos = alumnosInscritos.size();
		int cantidadDocentesNecesarios = (cantidadAlumnos - 1) / 20 + 1;

		if (cantidadDocentesNecesarios > docentesDisponibles.size()) {
			return false;
		}

		for (int i = 0; i < cantidadDocentesNecesarios; i++) {
			Profesor docente = docentesDisponibles.get(i);
			docentes.add(docente);
		}

		return true;
	}

	public List<Alumno> getAlumnosInscriptos() {
		return alumnosInscritos;
	}

	public boolean registrarNotaParcial(Alumno alumno, String tipoNota, int nota) {
		if (alumno != null && tipoNota != null && (tipoNota.equals("1erParc") || tipoNota.equals("2doParc"))
				&& nota >= 1 && nota <= 10) {
			Map<String, Nota> notasAlumno = notasPorAlumno.get(alumno);
			if (notasAlumno == null) {
				notasAlumno = new HashMap<>();
			}
			notasAlumno.put(tipoNota, new Nota(alumno, tipoNota, nota));
			notasPorAlumno.put(alumno, notasAlumno);
			return true;
		}
		return false;
	}

	public boolean registrarNotaRecuperatorio(Alumno alumno, String tipoNota, int nota) {
		if (alumno != null && tipoNota != null && tipoNota.equals("Recuperatorio") && nota >= 1 && nota <= 10) {
			Map<String, Nota> notasAlumno = notasPorAlumno.get(alumno);
			if (notasAlumno != null && (notasAlumno.containsKey("1erParc") || notasAlumno.containsKey("2doParc"))) {
				if (!alumno.haRendidoRecuperatorio()) {
					notasAlumno.put(tipoNota, new Nota(alumno, tipoNota, nota));
					notasPorAlumno.put(alumno, notasAlumno);
					alumno.marcarRecuperatorioRendido();
					return true;
				}
			}
		}
		return false;
	}

	public boolean registrarNotaFinal(Alumno alumno, int nota) {
		if (alumno != null && nota >= 1 && nota <= 10) {
			Map<String, Nota> notasAlumno = notasPorAlumno.get(alumno);
			if (notasAlumno != null) {
				if (notasAlumno.containsKey("1erParc") && notasAlumno.containsKey("2doParc")) {
					if (nota >= 4) {

						notasAlumno.put("Final", new Nota(alumno, "Final", nota));
						notasPorAlumno.put(alumno, notasAlumno);
						return true;
					}
				} else if (notasAlumno.containsKey("Recuperatorio")) {
					if (nota >= 4) {

						notasAlumno.put("Final", new Nota(alumno, "Final", nota));
						notasPorAlumno.put(alumno, notasAlumno);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean tieneNota(Universidad universidad, String tipoNota) {
		if (universidad != null && tipoNota != null
				&& (tipoNota.equals("1erParc") || tipoNota.equals("2doParc") || tipoNota.equals("Final"))) {
			for (Alumno inscrito : alumnosInscritos) {
				if (inscrito.equals(universidad)) {
					for (Alumno alumnoConNotas : notasPorAlumno.keySet()) {
						if (alumnoConNotas.equals(universidad)) {
							Map<String, Nota> notasAlumno = notasPorAlumno.get(alumnoConNotas);
							if (notasAlumno.containsKey(tipoNota)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
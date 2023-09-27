package ar.edu.unlam.pb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Universidad {

	private String nombreUniversidad;
	private Set<Alumno> alumnos;
	private Set<Materia> materias;
	private Set<Profesor> profesores;
	private Set<Comision> comisiones;
	private Set<CicloLectivo> ciclosLectivos;
	private Set<Aula> aulas;
	private List<Materia> materiaList;

	public Universidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
		this.materias = new HashSet<>();
		this.alumnos = new HashSet<>();
		this.setProfesores(new HashSet<>());
		this.comisiones = new HashSet<>();
		this.setCiclosLectivos(new HashSet<>());
		this.aulas = new HashSet<>();
		this.setMateriaList(new ArrayList<>());
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

	public int getCantidadDeAlumnos() {
		return alumnos.size();
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

	public boolean agregarComision(Comision comision) {
		for (Comision existingComision : comisiones) {
			if (existingComision.getMateria().equals(comision.getMateria())) {
				return false;
			}
		}
		comisiones.add(comision);
		return true;
	}

	public boolean agregarAula(Aula aula) {
		if (!aulas.contains(aula)) {
			aulas.add(aula);
			return true;
		}
		return false;
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}

	public Set<CicloLectivo> getCiclosLectivos() {
		return ciclosLectivos;
	}

	public void setCiclosLectivos(Set<CicloLectivo> ciclosLectivos) {
		this.ciclosLectivos = ciclosLectivos;
	}

	public List<Materia> getMateriaList() {
		return materiaList;
	}

	public void setMateriaList(List<Materia> materiaList) {
		this.materiaList = materiaList;
	}

	public boolean agregarCicloLectivo(CicloLectivo cicloLectivo) {
		for (CicloLectivo cicloExistente : ciclosLectivos) {
			if (cicloExistente.getFechaDeInicio().equals(cicloLectivo.getFechaDeInicio())
					&& cicloExistente.getFechaFin().equals(cicloLectivo.getFechaFin())) {
				return false;
			}
		}

		ciclosLectivos.add(cicloLectivo);
		return true;
	}

	public int getCantidadDeCiclosLectivos() {
		return ciclosLectivos.size();
	}

	public boolean asignarDosDocentesALaComision(Comision comision, Profesor docente1, Profesor docente2) {
		if (!comision.asignarDocente(docente1) || !comision.asignarDocente(docente2)) {
			return false;
		}
		return true;
	}

	public Boolean inscribirAlumnoALaComision(Alumno alumno2, String idComision, LocalDate fechaInscripcion,
			int notaAprobacion) {
		Alumno alumnoEnUniversidad = buscarAlumnoPorDni(alumno2.getDni());
		if (alumnoEnUniversidad == null) {
			return false;
		}
		Comision comision = buscarComisionPorId(idComision);

		if (alumnoEnUniversidad != null && comision != null) {
			if (notaAprobacion >= comision.getNotaAprobacion()
					&& alumnoEnUniversidad.tieneCorrelativasAprobadas(comision.getMateria())
					&& !alumnoEnUniversidad.estaInscritoEnOtraComisionEnElMismoHorario(comision.getDia(),
							comision.getHorario())
					&& comision.tieneCapacidadDisponible()
					&& !alumnoEnUniversidad.tieneMateriasAprobadas(comision.getMateria())
					&& fechaInscripcion.isAfter(comision.getFechaInicioInscripcion())
					&& fechaInscripcion.isBefore(comision.getFechaFinInscripcion())) {
				comision.inscribirAlumno(alumnoEnUniversidad, notaAprobacion, fechaInscripcion);
				return true;
			}
		}
		return false;
	}

	private Alumno buscarAlumnoPorDni(Integer dni) {
		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(dni)) {
				return alumno;
			}
		}
		return null;
	}

	private Comision buscarComisionPorId(String idComision) {
		for (Comision comision : comisiones) {
			if (comision.getId().equals(idComision)) {
				return comision;
			}
		}
		return null;
	}

	private Profesor buscarDocentePorDNI(Integer dni) {
		for (Profesor docente : profesores) {
			if (docente.getProfesorId().equals(dni)) {
				return docente;
			}
		}
		return null;
	}

	private Profesor buscarDocenteDisponible() {
		for (Profesor docente : profesores) {
			boolean docenteAsignado = false;
			for (Comision comision : comisiones) {
				if (comision.getDocentes().contains(docente)) {
					docenteAsignado = true;
					break;
				}
			}
			if (!docenteAsignado) {
				return docente;
			}
		}
		return null;
	}

	public boolean registrarNota(String idComision, Integer idAlumno, Integer nota, String tipoNota) {
		Comision comision = buscarComisionPorId(idComision);
		if (comision == null) {
			return false;
		}

		Alumno alumno = buscarAlumnoPorDni(idAlumno);
		if (alumno == null) {
			return false;
		}

		if (nota < 1 || nota > 10) {
			return false;
		}

		if (tipoNota.equals("1erParc") || tipoNota.equals("2doParc")) {
			if (!alumno.tieneCorrelativasAprobadas(comision.getMateria())) {
				return false;
			}
			alumno.registrarNotaParcial(comision, tipoNota, nota);
		} else if (tipoNota.equals("Rec1Parcial") || tipoNota.equals("Rec2Parcial")) {
			if (alumno.haRendidoRecuperatorio()) {
				return false;
			}
			alumno.registrarNotaRecuperatorio(comision, tipoNota, nota);
		} else if (tipoNota.equals("Final")) {
			if (!alumno.haAprobadoParciales(comision)) {
				return false;
			}
			alumno.registrarNotaFinal(comision, nota, tipoNota);
		} else {
			return false;
		}

		return true;
	}

	public boolean tieneMateriasAprobadas(Materia materia, Comision comision) {
		List<String> tiposDeNotasNecesarias = new ArrayList<>();
		tiposDeNotasNecesarias.add("1erParc");
		tiposDeNotasNecesarias.add("2doParc");
		tiposDeNotasNecesarias.add("Final");

		for (String tipoNota : tiposDeNotasNecesarias) {
			if (!comision.tieneNota(this, tipoNota)) {
				return false;
			}
		}

		return true;
	}

}

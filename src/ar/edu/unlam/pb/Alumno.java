package ar.edu.unlam.pb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Alumno {

	private String nombre;
	private String apellido;
	private Integer dni;

	private Set<String> materiasAprobadas;
	private List<Comision> comisionesInscriptas;
	private List<String> correlativasAprobadas;
	private List<String> materiasConNota;
	private List<Nota> notas;
	private List<Materia> materiasAprobada;

	public Alumno(String nombre, String apellido, Integer dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.comisionesInscriptas = new ArrayList<>();
		this.materiasAprobadas = new HashSet<>();
		this.correlativasAprobadas = new ArrayList<>();
		this.materiasConNota = new ArrayList<>();
		this.setNotas(new ArrayList<>());
		this.setMateriasAprobada(new ArrayList<>());
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

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public boolean puedeCursarMateria(Materia materia) {
		List<String> correlativasRequeridas = materia.getCorrelativasRequeridas();
		for (String correlativa : correlativasRequeridas) {
			if (!materiasAprobadas.contains(correlativa)) {
				return false;
			}
		}
		return true;
	}

	public void aprobarCorrelativa(String correlativa) {
		correlativasAprobadas.add(correlativa);
	}

	public Set<String> getMateriasAprobadas() {
		return materiasAprobadas;
	}

	public List<Comision> getComisionesInscriptas() {
		return comisionesInscriptas;
	}

	public boolean estaInscritoEnOtraComisionEnElMismoHorario(String dia, String horario) {
		for (Comision comision : comisionesInscriptas) {
			if (comision.getDia().equals(dia) && comision.getHorario().equals(horario)) {
				return true;
			}
		}
		return false;
	}

	public boolean tieneMateriasAprobadas(Materia materia) {
		return materiasAprobadas.contains(materia.getCodigoMateria());
	}

	public void inscribirComision(Comision comision) {
		comisionesInscriptas.add(comision);
	}

	public void aprobarMateria(String codigoMateria) {
		materiasAprobadas.add(codigoMateria);
	}

	public List<String> getCorrelativasAprobadas() {
		return correlativasAprobadas;
	}

	public boolean tieneCorrelativasAprobadas(Materia materia) {
		List<String> correlativasRequeridas = materia.getCorrelativasRequeridas();

		if (correlativasRequeridas.isEmpty()) {
			return true;
		}

		for (String correlativa : correlativasRequeridas) {
			if (!correlativasAprobadas.contains(correlativa)) {
				return false;
			}
		}

		return true;
	}

	private boolean rendioRecuperatorio = false;

	public boolean haRendidoRecuperatorio() {
		return rendioRecuperatorio;
	}

	public void marcarRecuperatorioRendido() {
		rendioRecuperatorio = true;
	}

	public void registrarNota(String codigoMateria) {
		materiasConNota.add(codigoMateria);
	}

	public boolean tieneNota(String codigoMateria) {
		return materiasConNota.contains(codigoMateria);
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public void registrarNotaParcial(Comision comision, String tipoNota, int valor) {
		Alumno alumno = null;
		if (tipoNota.equals("1erParc") || tipoNota.equals("2doParc")) {
			Nota nota = new Nota(alumno, tipoNota, valor);
			notas.add(nota);
		}
	}

	public void registrarNotaRecuperatorio(Comision comision, String tipoNota, int valor) {

		Alumno alumno = null;
		if (tipoNota.equals("Rec1Parcial") || tipoNota.equals("Rec2Parcial")) {
			Nota nota = new Nota(alumno, tipoNota, valor);
			notas.add(nota);
		}
	}

	public void registrarNotaFinal(Comision comision, int valor, String tipoNota) {
		Alumno alumno = null;
		Nota nota = new Nota(alumno, tipoNota, valor);
		notas.add(nota);
	}

	public boolean haAprobadoParciales(Comision comision) {
		int primerParcial = 0;
		int segundoParcial = 0;

		for (Nota nota : notas) {
			if (nota.getTipoNota().equals("1erParc")) {
				primerParcial = nota.getValor();
			} else if (nota.getTipoNota().equals("2doParc")) {
				segundoParcial = nota.getValor();
			}
		}

		return primerParcial >= 4 && segundoParcial >= 4;
	}

	public void aprobarMateria(Materia materiaAprobada) {
		materiasAprobadas.add(materiaAprobada.getCodigoMateria());
	}

	public boolean tieneMateriasAprobadas(Alumno alumno, Materia materia) {
		if (alumno != null) {
			return alumno.tieneMateriasAprobadas(materia);
		}
		return false;
	}

	public void agregarMateriaAprobada(Materia materia) {
		materiasAprobadas.add(materia);
	}

	public List<String> obtenerMateriasAprobadas() {
		return new ArrayList<>(materiasAprobadas);
	}

	public Set<String> obtenerMateriasAprobadasDeAlumno(Alumno alumno) {
		return alumno.getMateriasAprobadas();
	}

	public void setCorrelativasAprobadas(List<String> correlativasAprobadas) {
		this.correlativasAprobadas = correlativasAprobadas;
	}

	public List<Materia> getMateriasAprobada() {
		return materiasAprobada;
	}

	public void setMateriasAprobada(List<Materia> materiasAprobada) {
		this.materiasAprobada = materiasAprobada;
	}

}

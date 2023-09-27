package ar.edu.unlam.pb;

import java.time.LocalDate;

public class CicloLectivo {
	private String cicloEnCurso;
	private LocalDate fechaDeInicio;
	private LocalDate fechaFin;

	CicloLectivo(LocalDate fechaInicio, LocalDate fechaFin, String cicloEnCurso) {
		this.cicloEnCurso = cicloEnCurso;
		this.fechaDeInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getCicloEnCurso() {
		return cicloEnCurso;
	}

	public void setCicloEnCurso(String cicloEnCurso) {
		this.cicloEnCurso = cicloEnCurso;
	}

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

}

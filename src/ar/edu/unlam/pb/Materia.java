package ar.edu.unlam.pb;

public class Materia {
	
	private String nombreMateria;
	private String codigoMateria;
	private Double nota;
	
	public Materia(String nombreMateria, String codigoMateria, Double nota) {
		
		this.nombreMateria = nombreMateria;
		this.codigoMateria = codigoMateria;
		this.nota = 0.0;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public String getCodigoMateria() {
		return codigoMateria;
	}

	public void setCodigoMateria(String codigoMateria) {
		this.codigoMateria = codigoMateria;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	

}

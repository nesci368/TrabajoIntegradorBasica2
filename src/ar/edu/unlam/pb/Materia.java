package ar.edu.unlam.pb;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String codigoMateria;
    private Double nota;
    private String nombreMateria;
    private List<String> correlativasRequeridas;
    private List <Materia> correlativas;

    public Materia(String nombreMateria, String codigoMateria, Double nota) {
    	this.nombreMateria = nombreMateria;
        this.codigoMateria = codigoMateria;
        this.correlativasRequeridas = new ArrayList<>();
        this.correlativas = new ArrayList<>();
        this.nota = 0.0;
    }

    public Materia(String codigoDeMateria) {
    	this.codigoMateria = codigoDeMateria;
	}

	public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public void agregarCorrelativa(String codigoCorrelativa) {
        correlativasRequeridas.add(codigoCorrelativa);
    }

    public List<String> getCorrelativasRequeridas() {
        return correlativasRequeridas;
    }

    public boolean eliminarCorrelativa(String codigoCorrelativa) {
        return correlativasRequeridas.remove(codigoCorrelativa);
    }

    @Override
    public String toString() {
        return "Materia [codigoMateria=" + codigoMateria + ", correlativasRequeridas=" + correlativasRequeridas + "]";
    }

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public void setCorrelativasRequeridas(List<String> correlativasRequeridas) {
		this.correlativasRequeridas = correlativasRequeridas;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	public boolean agregarCorrelativa(Materia materia) {
	    return correlativas.add(materia);
	}

	public List<Materia> getCorrelativas() {
	    return correlativas;
	}

	public String getCicloLectivo() {
		// TODO Auto-generated method stub
		return null;
	}
	
    
    
}

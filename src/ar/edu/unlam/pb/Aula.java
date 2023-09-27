package ar.edu.unlam.pb;

import java.util.Objects;

public class Aula {
	private String numeroAula;

	Aula(String numeroAula) {
		this.numeroAula = numeroAula;
	}

	public String getNumeroAula() {
		return numeroAula;
	}

	public void setNumeroAula(String numeroAula) {
		this.numeroAula = numeroAula;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Aula aula = (Aula) o;
		return Objects.equals(numeroAula, aula.numeroAula);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroAula);
	}

}

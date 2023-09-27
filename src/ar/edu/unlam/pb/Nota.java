package ar.edu.unlam.pb;

public class Nota {
    private Alumno alumno;
    private String tipoNota;
    private int nota;

    public Nota(Alumno alumno, String tipoNota, int nota) {
        this.alumno = alumno;
        this.tipoNota = tipoNota;
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

	public int getValor() {
		
		return 0;
	}
}


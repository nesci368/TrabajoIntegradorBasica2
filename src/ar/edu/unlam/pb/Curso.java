package ar.edu.unlam.pb;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private Integer id;
    private String nombre;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public Curso(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }
}

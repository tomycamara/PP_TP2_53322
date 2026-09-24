package modelo;

import modelo.actividades.Actividad;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;


public class Inscripcion implements Serializable {

    private LocalDate fecha;
    private Actividad actividad;
    private Estudiante estudiante;
    private String estadoActual;


    //mejorar a futuro
    private final Map<Integer, String> mapaEstados = Map.of(
            1, "ACTIVA",
            2, "BLOQUEADA",
            3, "EN PAUSA"

    );


    public Inscripcion(Actividad actividad, Estudiante estudiante) {
        this.actividad = actividad;
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estadoActual = mapaEstados.get(1);
    }

    public void mostrarDatos(){
        System.out.println("-----------------------");
        System.out.println("Nombre alumno: " + this.estudiante.getNombre());
        System.out.println("Legajo: " + this.estudiante.getLegajo());
        System.out.println("Fecha de Inscripcion: " + this.fecha);
        System.out.println("Estado: " + this.estadoActual);
        System.out.println("-----------------------");
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }
}

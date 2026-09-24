package modelo.actividades;


import excepciones.CupoExcedidoException;
import modelo.*;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int cupoMinimo;
    private ArrayList<Inscripcion> inscripciones;

    static {
        cupoMinimo = 1;
    }

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<Inscripcion>();

    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if(inscripciones.size() <= cupoMaximo){
    Inscripcion inscripcion = new Inscripcion(this, estudiante);
    inscripciones.add(inscripcion);
    return inscripcion;

    } else{

    throw new CupoExcedidoException("Cupo lleno");
}
    }
    public void mostrarDatosAct(){

        System.out.println("Nombre: "+ this.titulo);
        System.out.println("Id: "+ this.id);
        System.out.println("Cupo Maximo: "+ this.cupoMaximo);
        System.out.println("----------------------");
        System.out.println("---INSCRIPCIONES---");
        mostrarInscripciones();
    }

    public void mostrarInscripciones(){
        for (Inscripcion i: inscripciones) {
            i.mostrarDatos();
        }

    }
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
}
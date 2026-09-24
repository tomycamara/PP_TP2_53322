package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.*;

public class Curso  extends Actividad implements Certificable {
private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override
    public String getTipo(){
        final String nombre;
        nombre = this.getClass().getTypeName();
        return nombre;
    }

    @Override
    public double calcularCostoMateriales() {
        return 100;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
     String texto = (ENTIDAD_EMISORA+" Certifica que el Alumno "+ estudiante.getNombre() +" con legajo: " + estudiante.getLegajo()+ " ha participado del curso: "+ getTitulo() +".");

    return texto;
    }
}

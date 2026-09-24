package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Taller  extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    public String getTipo(){
        return this.getClass().getTypeName();
    }

    @Override
    public double calcularCostoMateriales() {
        if (requiereNotebook){
            return 5000;
        } else{
            return 2000;
        }

    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
    String texto = (ENTIDAD_EMISORA+" Certifica que el Alumno "+ estudiante.getNombre() +" con legajo: " + estudiante.getLegajo()+ " ha participado del taller: "+ getTitulo()+".");
    return texto;
    }
}

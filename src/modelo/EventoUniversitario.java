package modelo;

import modelo.actividades.*;
import modelo.certificacion.Certificable;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private  boolean gratuito;
    private static int cantidadEventos;
    Sala sala;
    private List<Actividad> actividades;

    static {
        cantidadEventos = 0;
    }

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<Actividad>();
        cantidadEventos++;
    }

    public EventoUniversitario( EventoUniversitario EventoUniversitarioOriginal){
        this.id = EventoUniversitarioOriginal.id;
        this.titulo = EventoUniversitarioOriginal.titulo;
        this.costoBase = EventoUniversitarioOriginal.costoBase;
        this.gratuito = EventoUniversitarioOriginal.gratuito;
        this.actividades = EventoUniversitarioOriginal.actividades;
        cantidadEventos++;
    }

    public double calcularCostoEstimado(){
        double costoTotal = costoBase;
        for (Actividad i: actividades){
            costoTotal += i.calcularCostoMateriales();

        }
        System.out.println("El costo total estimado es: $" + costoTotal);
        return costoTotal*1.21;
    }

    public void asignarSala(Sala sala){
        this.sala = sala;

    }

    public void crearActividad(int id, String titulo, int cupo, boolean requiereNotebook){
        actividades.add(new Taller(id,titulo,cupo, requiereNotebook));
    }
    public void crearActividad(int id, String titulo, int cupo, String disertante){
        actividades.add(new Charla(id,titulo,cupo, disertante));
    }
    public void crearActividad(int id, String titulo, int cupo, int nivel){
        actividades.add(new Curso(id,titulo,cupo, nivel));
    }


    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo){
        ArrayList<T> filtradas = new ArrayList<>();
        for (Actividad a: actividades){
            if(tipo.isInstance(a)){
                filtradas.add(tipo.cast(a));
            }
        }
    return filtradas;
    }

    public <T extends Actividad> double calcularCostoMateriales(List<? extends Actividad>  calculadas){
        double sumaMateriales = 0;
        for(Actividad a: calculadas){
            sumaMateriales += a.calcularCostoMateriales();
        }

        return sumaMateriales;
    }



    public void mostrarDatos(){
        System.out.println("-----------EVENTO------------");
        System.out.println("Titulo: " + titulo);
        System.out.println("Id: " + id);
      System.out.println("Costo base: " + costoBase);
      System.out.println("Costo estimado: " + this.calcularCostoEstimado());
      System.out.println("Gratuito: " + gratuito);
      System.out.println("modelo.Sala: " + (sala != null ? sala.getNombre() : "Sin sala"));
      System.out.println("modelo.Sala ID: " + (sala != null ? sala.getId() : "Sin sala"));
      for(Actividad a: actividades) {
          System.out.println("-----------ACTIVIDAD------------");
          a.mostrarDatosAct();
          System.out.println("-----------------------");
      }
      System.out.println("-----------------------");
    }

public void certificados(){
    for (Actividad actividad: this.getActividades()){
        if (actividad instanceof Certificable certificable){
            System.out.println("CERTIFICADOS EMITIDOS PARA LA ACTIVIDAD " + actividad.getTitulo());
            for (Inscripcion inscripcion: actividad.getInscripciones()){
                String certificado = certificable.generarCertificado(inscripcion.getEstudiante());
                System.out.println(certificado);
            }
        }
    }

}




public void persistir() throws IOException {
        FileOutputStream cano = new FileOutputStream(this.id+".dat");
        ObjectOutputStream archivo = new ObjectOutputStream(cano);
        archivo.writeObject(this);
        archivo.close();
        cano.close();

}  //probar try-with-resources
public static EventoUniversitario leerEvento(int id) throws IOException, ClassNotFoundException {
    EventoUniversitario evento = null;
    FileInputStream archivo = new FileInputStream(id+".dat");
    ObjectInputStream ois = new ObjectInputStream(archivo);
    evento = (EventoUniversitario)  ois.readObject();
    return new EventoUniversitario(evento);
}
    public List<Actividad>  getActividades() {
        return Collections.unmodifiableList(actividades);
    }


    public static int getCantidadEventos(){
        return cantidadEventos;
    }

    public String getTitulo() {
        return titulo;
    }
}

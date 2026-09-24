import modelo.*;
import excepciones.*;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EventoUniversitario evento = new EventoUniversitario("1", "primer evento", 300000, false);
        EventoUniversitario evento2 = new EventoUniversitario("2", "segundo evento", 3000, true);
        EventoUniversitario evento3 = new EventoUniversitario("3", "tercer evento", 10000, false);

        System.out.println("Cantidad de eventos creados: " + EventoUniversitario.getCantidadEventos());

        Estudiante est1 = new Estudiante("53322", "Tomás Cámara");
        Estudiante est2 = new Estudiante("52135", "Gabriel Saw");
        Estudiante est3 = new Estudiante("89798", "Franco Araya");
        Estudiante est4 = new Estudiante("67676", "Pedro Picapiedra");

        Sala sala1 = new Sala(1, "primer sala");
        Sala sala2 = new Sala(2, "segunda sala");
        Sala sala3 = new Sala(3, "tercer sala");

        evento.asignarSala(sala1);
        evento2.asignarSala(sala2);
        evento3.asignarSala(sala3);

        //CREACION DE ACTIVIDADES
        evento.crearActividad(1, "Charla 1", 56, "Santaolalla");
        evento.crearActividad(2, "Charla 2", 2, "Rolon");
        evento.crearActividad(3, "Taller de Arduino", 22, true);
        evento.crearActividad(4, "Taller de RCP", 34, false);
        evento.crearActividad(56, "Taller de Cocina", 78, false);
        evento.crearActividad(9, "Primer curso", 25, 1);
        evento.crearActividad(67, "Segundo curso", 11, 6);

        evento2.crearActividad(44, "taller carpinteria", 15, false);
        evento2.crearActividad(67, "taller de soldadura", 25, true);

        evento3.crearActividad(95, "charla importante", 78, "Shakespeare");
        evento3.crearActividad(10, "Boca jrs", 12, "Palermo");
        evento3.crearActividad(23, "Cursito", 14, 3);


        //INSCRIPCIONES
        try {
            System.out.println("----Comienza el Período de Inscripciones----");
            evento.getActividades().get(0).inscribir(est1);
            evento.getActividades().get(1).inscribir(est2);
            evento.getActividades().get(2).inscribir(est2);
            evento.getActividades().get(3).inscribir(est3);
            evento.getActividades().get(4).inscribir(est1);
            evento.getActividades().get(5).inscribir(est2);
            evento.getActividades().get(6).inscribir(est1);
            evento.getActividades().get(0).inscribir(est2);
            evento.getActividades().get(1).inscribir(est3);
            evento.getActividades().get(2).inscribir(est4);

            evento2.getActividades().get(0).inscribir(est1);
            evento2.getActividades().get(0).inscribir(est2);
            evento2.getActividades().get(1).inscribir(est3);
            evento2.getActividades().get(1).inscribir(est4);

            evento3.getActividades().get(0).inscribir(est1);
            evento3.getActividades().get(1).inscribir(est2);
            evento3.getActividades().get(2).inscribir(est3);
            evento3.getActividades().get(0).inscribir(est4);


        }
        catch (CupoExcedidoException e){
            System.err.println("Cupo Exedido");
        }finally {
            System.out.println("--Finalizo el período de inscripciones.--");

           // Desactivado  evento.mostrarDatos();
            evento2.certificados();
        }



        //PERSISTENCIA
        try{
            System.out.println("Se persistira el evento: " +evento2.getTitulo());
            evento2.persistir();
        } catch (IOException e) {
            System.err.println("No se puedo guardar el evento." + e.getMessage());
        } finally {
            System.out.println("Finalizo la persistencia");
        }

        //LEIDA DEL EVENTO GUARDADO
        EventoUniversitario leido = null;
        try{
            System.out.println("Se leerá el evento con id 2.");
            leido = EventoUniversitario.leerEvento(2);

        } catch(ClassNotFoundException e){
            System.err.println("No se encontro la clase. " +e.getMessage());
        }catch (FileNotFoundException e){
            System.err.println("No se encontro el archivo. "+ e.getMessage());
        } catch (IOException e){
            System.err.println("Error de E/S. "+ e.getMessage());
        }
        finally {
           // leido.mostrarDatos();
            System.out.println("Finalizó la lectura del evento leido.");
        }

        //Activiades Filtradas (de evento):
        System.out.println("---Filtro de Actividades---");
        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
        System.out.println("Charlas totales:" +charlas.size());
        List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);
        System.out.println("Talleres totales:" +talleres.size());
        List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);
        System.out.println("Cursos totales:" +cursos.size());



        //Calcular costos
        System.out.println("---Costo según tipo de  Actividad---");
        System.out.println("El costo de las charlas es: $"+ evento.calcularCostoMateriales(charlas));
        System.out.println("El costo de los talleres es: $"+ evento.calcularCostoMateriales(talleres));
        System.out.println("El costo de las cursos es: $"+ evento.calcularCostoMateriales(cursos));


    }
}
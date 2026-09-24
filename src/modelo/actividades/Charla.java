package modelo.actividades;

public class Charla  extends Actividad {

    private String disertante;

    public Charla(int id, String titulo, int cupoMaximo, String disertante) {
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }

    public String getTipo(){
    return this.getClass().getTypeName();
    }

    @Override
    public double calcularCostoMateriales() {
        return 0;
    }
}

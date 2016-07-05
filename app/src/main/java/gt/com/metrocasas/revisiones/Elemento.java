package gt.com.metrocasas.revisiones;

public class Elemento {

    private String proyecto;
    private String clasficacion;
    private String elemento;

    public Elemento(String proyecto, String clasficacion, String elemento) {
        this.proyecto = proyecto;
        this.clasficacion = clasficacion;
        this.elemento = elemento;
    }

    public String getProyecto() {
        return proyecto;
    }

    public String getClasficacion() {
        return clasficacion;
    }

    public String getElemento() {
        return elemento;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public void setClasficacion(String clasficacion) {
        this.clasficacion = clasficacion;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
}

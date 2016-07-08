package gt.com.metrocasas.revisiones;

public class Elemento {

    private String id;
    private String proyecto;
    private String clasficacion;
    private String elemento;
    private boolean estado;
    private String comentario;
    private String imagen;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

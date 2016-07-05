package gt.com.metrocasas.revisiones;

public class Revision {

    private String proyecto;
    private String fechaRevision;
    private String elemento;
    private Integer estado;
    private String foto;
    private String comentario;

    public Revision(String proyecto, String fechaRevision, String elemento, Integer estado, String foto, String comentario) {
        this.proyecto = proyecto;
        this.fechaRevision = fechaRevision;
        this.elemento = elemento;
        this.estado = estado;
        this.foto = foto;
        this.comentario = comentario;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

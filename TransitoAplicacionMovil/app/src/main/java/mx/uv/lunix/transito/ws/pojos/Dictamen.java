package mx.uv.lunix.transito.ws.pojos;

public class Dictamen {

    private Integer Folio;
    private String Descripcion;
    private  String fechaDictamen;
    private String estado;
    private Integer IdUsuario;

    public Dictamen(Integer folio, String descripcion, String fechaDictamen, String estado, Integer idUsuario) {
        Folio = folio;
        Descripcion = descripcion;
        this.fechaDictamen = fechaDictamen;
        this.estado = estado;
        IdUsuario = idUsuario;
    }

    public Integer getFolio() {
        return Folio;
    }

    public void setFolio(Integer folio) {
        Folio = folio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFechaDictamen() {
        return fechaDictamen;
    }

    public void setFechaDictamen(String fechaDictamen) {
        this.fechaDictamen = fechaDictamen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
}
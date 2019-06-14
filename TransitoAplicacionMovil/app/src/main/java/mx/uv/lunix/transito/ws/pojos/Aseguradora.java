package mx.uv.lunix.transito.ws.pojos;

public class Aseguradora {
    private Integer IdAseguradora;
    private String nombre;

    public Aseguradora(Integer idAseguradora, String nombre) {
        IdAseguradora = idAseguradora;
        this.nombre = nombre;
    }

    public Integer getIdAseguradora() {
        return IdAseguradora;
    }

    public void setIdAseguradora(Integer idAseguradora) {
        IdAseguradora = idAseguradora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

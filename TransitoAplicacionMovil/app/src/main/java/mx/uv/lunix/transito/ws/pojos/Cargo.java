package mx.uv.lunix.transito.ws.pojos;

public class Cargo {
    private Integer IdCargo;
    private String  nombreCargo;


    public Cargo(Integer idCargo, String nombreCargo) {
        IdCargo = idCargo;
        this.nombreCargo = nombreCargo;
    }

    public Integer getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(Integer idCargo) {
        IdCargo = idCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }
}

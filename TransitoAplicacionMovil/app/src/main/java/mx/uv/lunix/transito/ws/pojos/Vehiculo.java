package mx.uv.lunix.transito.ws.pojos;

public class Vehiculo {
    private String Placas;
    private String Marca;
    private String Modelo;
    private Integer Anio;
    private String Color;
    private Integer Idaseguradora;
    private String PolizaSeguro;
    private Integer Idcoductor;

    public Vehiculo(String placas, String marca, String modelo, Integer anio, String color, Integer idaseguradora, String polizaSeguro, Integer idcoductor) {
        Placas = placas;
        Marca = marca;
        Modelo = modelo;
        Anio = anio;
        Color = color;
        Idaseguradora = idaseguradora;
        PolizaSeguro = polizaSeguro;
        Idcoductor = idcoductor;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Integer getAnio() {
        return Anio;
    }

    public void setAnio(Integer anio) {
        Anio = anio;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public Integer getIdaseguradora() {
        return Idaseguradora;
    }

    public void setIdaseguradora(Integer idaseguradora) {
        Idaseguradora = idaseguradora;
    }

    public String getPolizaSeguro() {
        return PolizaSeguro;
    }

    public void setPolizaSeguro(String polizaSeguro) {
        PolizaSeguro = polizaSeguro;
    }

    public Integer getIdcoductor() {
        return Idcoductor;
    }

    public void setIdcoductor(Integer idcoductor) {
        Idcoductor = idcoductor;
    }
}

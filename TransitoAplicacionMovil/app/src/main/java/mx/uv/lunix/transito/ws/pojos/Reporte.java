package mx.uv.lunix.transito.ws.pojos;

public class Reporte {

    private Integer Idreporte;
    private double Longitud;
    private double Latitud;
    private String Lugar;
    private String NombreImplicado;
    private Integer IdAAeguradora;
    private String NumeroPolizaImplicado;
    private String MarcaImplicado;
    private String ModeloImplicado;
    private String ColorImplicado;
    private String NumeroPlacasImplicado;
    private String FechaSuceso;
    private byte[] Fotos;
    private Integer DictamenFolio;
    private Integer Idevidencia;
    private Integer IdConductor;


    public Reporte(Integer idreporte, double longitud, double latitud, String lugar, String nombreImplicado,
                   Integer idAAeguradora, String numeroPolizaImplicado, String marcaImplicado, String modeloImplicado,
                   String colorImplicado,String numeroPlacasImplicado, String fechaSuceso, byte[] fotos, Integer dictamenFolio, Integer idevidencia, Integer IdConductor) {
        Idreporte = idreporte;
        Longitud = longitud;
        Latitud = latitud;
        Lugar = lugar;
        NombreImplicado = nombreImplicado;
        IdAAeguradora = idAAeguradora;
        NumeroPolizaImplicado = numeroPolizaImplicado;
        MarcaImplicado = marcaImplicado;
        ModeloImplicado = modeloImplicado;
        ColorImplicado = colorImplicado;
        NumeroPlacasImplicado = numeroPlacasImplicado;
        FechaSuceso = fechaSuceso;
        Fotos = fotos;
        DictamenFolio = dictamenFolio;
        Idevidencia = idevidencia;
        IdConductor = IdConductor;
    }

    public Integer getIdreporte() {
        return Idreporte;
    }

    public void setIdreporte(Integer idreporte) {
        Idreporte = idreporte;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getNombreImplicado() {
        return NombreImplicado;
    }

    public void setNombreImplicado(String nombreImplicado) {
        NombreImplicado = nombreImplicado;
    }

    public Integer getIdAAeguradora() {
        return IdAAeguradora;
    }

    public void setIdAAeguradora(Integer idAAeguradora) {
        IdAAeguradora = idAAeguradora;
    }

    public String getNumeroPolizaImplicado() {
        return NumeroPolizaImplicado;
    }

    public void setNumeroPolizaImplicado(String numeroPolizaImplicado) {
        NumeroPolizaImplicado = numeroPolizaImplicado;
    }

    public String getMarcaImplicado() {
        return MarcaImplicado;
    }

    public void setMarcaImplicado(String marcaImplicado) {
        MarcaImplicado = marcaImplicado;
    }

    public String getModeloImplicado() {
        return ModeloImplicado;
    }

    public void setModeloImplicado(String modeloImplicado) {
        ModeloImplicado = modeloImplicado;
    }

    public String getColorImplicado() {
        return ColorImplicado;
    }

    public void setColorImplicado(String colorImplicado) {
        ColorImplicado = colorImplicado;
    }

    public String getNumeroPlacasImplicado() {
        return NumeroPlacasImplicado;
    }

    public void setNumeroPlacasImplicado(String numeroPlacasImplicado) {
        NumeroPlacasImplicado = numeroPlacasImplicado;
    }

    public String getFechaSuceso() {
        return FechaSuceso;
    }

    public void setFechaSuceso(String fechaSuceso) {
        FechaSuceso = fechaSuceso;
    }

    public byte[] getFotos() {
        return Fotos;
    }

    public void setFotos(byte[] fotos) {
        Fotos = fotos;
    }

    public Integer getDictamenFolio() {
        return DictamenFolio;
    }

    public void setDictamenFolio(Integer dictamenFolio) {
        DictamenFolio = dictamenFolio;
    }

    public Integer getIdevidencia() {
        return Idevidencia;
    }

    public void setIdevidencia(Integer idevidencia) {
        Idevidencia = idevidencia;
    }

    public Integer getIdConductor() {
        return IdConductor;
    }

    public void setIdConductor(Integer idConductor) {
        IdConductor = idConductor;
    }
}

package mx.uv.lunix.transito.ws.pojos;

public class Conductor {

    private Integer IdConductor;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String FechaNacimiento;
    private String NumeroLicencia;
    private String Telefono;
    private String Password;

    public Conductor(Integer idConductor, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String numeroLicencia, String telefono, String password) {
        IdConductor = idConductor;
        Nombre = nombre;
        ApellidoPaterno = apellidoPaterno;
        ApellidoMaterno = apellidoMaterno;
        FechaNacimiento = fechaNacimiento;
        NumeroLicencia = numeroLicencia;
        Telefono = telefono;
        Password = password;
    }

    public Integer getIdConductor() {
        return IdConductor;
    }

    public void setIdConductor(Integer idConductor) {
        IdConductor = idConductor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getNumeroLicencia() {
        return NumeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        NumeroLicencia = numeroLicencia;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

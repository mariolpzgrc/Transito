package mx.uv.lunix.transito.ws.pojos;

public class Usuario {

    private Integer IdUsuario;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApelldoMaterno;
    private Integer IdCargo;
    private String Username;
    private String Password;

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
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

    public String getApelldoMaterno() {
        return ApelldoMaterno;
    }

    public void setApelldoMaterno(String apelldoMaterno) {
        ApelldoMaterno = apelldoMaterno;
    }

    public Integer getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(Integer idCargo) {
        IdCargo = idCargo;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Usuario(Integer idUsuario, String nombre, String apellidoPaterno, String apelldoMaterno, Integer idCargo, String username, String password) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        ApellidoPaterno = apellidoPaterno;
        ApelldoMaterno = apelldoMaterno;
        IdCargo = idCargo;
        Username = username;
        Password = password;
    }
}

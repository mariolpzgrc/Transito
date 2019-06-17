package mx.uv.lunix.transito.ws.pojos;

/**
 * Created by Carlos on 22/03/2018.
 */

public class Mensaje {
    private boolean error;
    private String mensaje;

    public Mensaje(){}

    public Mensaje(boolean error, String mensaje){
        this.error = error;
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
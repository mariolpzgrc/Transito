package mx.uv.lunix.transito.ws;


public class Responses {
    private boolean error;
    private int status;
    private String result;

    public Responses(){}

    public Responses(int status, String result, boolean error) {
        this.status = status;
        this.result = result;
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

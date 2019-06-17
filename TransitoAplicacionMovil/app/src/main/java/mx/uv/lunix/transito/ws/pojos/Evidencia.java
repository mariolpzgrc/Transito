package mx.uv.lunix.transito.ws.pojos;

public class Evidencia {

    private Integer Idevidencia;
    private byte[] FotoDerecha1;
    private byte[] FotoDerecha2;
    private byte[] FotoIzquierda1;
    private byte[] FotoIzquierda2;
    private byte[] FotoFrontal1;
    private byte[] FotoFrontal2;
    private byte[] FotoTrasera1;
    private byte[] FotoTrasera2;

    public Evidencia(Integer idevidencia, byte[] fotoDerecha1, byte[] fotoDerecha2, byte[] fotoIzquierda1, byte[] fotoIzquierda2, byte[] fotoFrontal1, byte[] fotoFrontal2, byte[] fotoTrasera1, byte[] fotoTrasera2) {
        Idevidencia = idevidencia;
        FotoDerecha1 = fotoDerecha1;
        FotoDerecha2 = fotoDerecha2;
        FotoIzquierda1 = fotoIzquierda1;
        FotoIzquierda2 = fotoIzquierda2;
        FotoFrontal1 = fotoFrontal1;
        FotoFrontal2 = fotoFrontal2;
        FotoTrasera1 = fotoTrasera1;
        FotoTrasera2 = fotoTrasera2;
    }

    public Integer getIdevidencia() {
        return Idevidencia;
    }

    public void setIdevidencia(Integer idevidencia) {
        Idevidencia = idevidencia;
    }

    public byte[] getFotoDerecha1() {
        return FotoDerecha1;
    }

    public void setFotoDerecha1(byte[] fotoDerecha1) {
        FotoDerecha1 = fotoDerecha1;
    }

    public byte[] getFotoDerecha2() {
        return FotoDerecha2;
    }

    public void setFotoDerecha2(byte[] fotoDerecha2) {
        FotoDerecha2 = fotoDerecha2;
    }

    public byte[] getFotoIzquierda1() {
        return FotoIzquierda1;
    }

    public void setFotoIzquierda1(byte[] fotoIzquierda1) {
        FotoIzquierda1 = fotoIzquierda1;
    }

    public byte[] getFotoIzquierda2() {
        return FotoIzquierda2;
    }

    public void setFotoIzquierda2(byte[] fotoIzquierda2) {
        FotoIzquierda2 = fotoIzquierda2;
    }

    public byte[] getFotoFrontal1() {
        return FotoFrontal1;
    }

    public void setFotoFrontal1(byte[] fotoFrontal1) {
        FotoFrontal1 = fotoFrontal1;
    }

    public byte[] getFotoFrontal2() {
        return FotoFrontal2;
    }

    public void setFotoFrontal2(byte[] fotoFrontal2) {
        FotoFrontal2 = fotoFrontal2;
    }

    public byte[] getFotoTrasera1() {
        return FotoTrasera1;
    }

    public void setFotoTrasera1(byte[] fotoTrasera1) {
        FotoTrasera1 = fotoTrasera1;
    }

    public byte[] getFotoTrasera2() {
        return FotoTrasera2;
    }

    public void setFotoTrasera2(byte[] fotoTrasera2) {
        FotoTrasera2 = fotoTrasera2;
    }
}



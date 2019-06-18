package mx.uv.lunix.transito.ws;



import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import mx.uv.lunix.transito.ws.pojos.Aseguradora;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Evidencia;
import mx.uv.lunix.transito.ws.pojos.Reporte;
import mx.uv.lunix.transito.ws.pojos.Vehiculo;


public class HttpUtils {

    private static String BASE_URL = "https://transitodesapps.azurewebsites.net/api/";
    private static final Integer CONNECT_TIMEOUT = 12000; //MILISECONDS
    private static final Integer READ_TIMEOUT = 12000; //MILISECONDS


    public static Responses login(String telefono, String contrasena) {
        String param = "{telefono:'" + telefono + "', password:'" +  contrasena + "'}";
        return invocarServicioWeb("Conductors/Login","POST",param);
    }

   /* public static  Responses registroConductor(Conductor conductor){
        String params = "{nombre:"+ conductor.getNombre() + "',apellidoPaterno:'" + conductor.getApellidoPaterno() +
                "',apellidoMaterno:'" + conductor.getApellidoMaterno() + "', fechNacimiento'" + conductor.getFechaNacimiento() +
                "', numeroLicencia:'" + conductor.getNumeroLicencia() + "', '"
        
        return invocarServicioWeb("Conductors", "POST",params);
    }*/

    public static Responses registroVehiculo(Vehiculo vehiculo){
        String param = String.format("{placas:%s,marca:%s,modelo:%s,anio:%s,color:%s,idaseguradora:%s,polizaSeguro:%s,idconductor:%s,idaseguradoraNavigation:%s,idconductorNavigation:%s}",
                vehiculo.getPlacas(), vehiculo.getModelo(), vehiculo.getAnio(), vehiculo.getColor(), vehiculo.getIdaseguradora(),vehiculo.getPolizaSeguro(), vehiculo.getIdcoductor());
        return invocarServicioWeb("vehiculoes", "POST", param);
    }

    public static Responses getVehiculos(Conductor conductor){
        return invocarServicioWeb("vehiculoes/" +conductor.getIdConductor(),"GET", null);
    }

    public static Responses getVehiculo(String id){
        return invocarServicioWeb("vehiculoes/"+id, "GET", null);
    }

    public static Responses registrarAseguradora(Aseguradora aseguradora){
        String param = "{nombre:'" + aseguradora.getNombre() + "'}";
        return  invocarServicioWeb("aseguradoras", "POST", param);
    }

    public static Responses crearReporte(Reporte reporte){
        String params = String.format("{longitud:%s,latiud:%s,lugar:%s, nombreImplicado:%s,aseguradoraImplicado:%s," +
                        "numeroPolizaImplicado:%s,marcaImplicado:%s,modeloImplicado:%s," +
                        "colorImplicado:%s,placaImplicado,fechaSuceso:%s,idevidencia:%s,idcconductor:%s}",
                reporte.getLongitud(), reporte.getLatitud(), reporte.getLugar(), reporte.getNombreImplicado(), reporte.getIdAAeguradora(),
                reporte.getNumeroPolizaImplicado(),reporte.getMarcaImplicado(),reporte.getModeloImplicado(),
                reporte.getColorImplicado(), reporte.getNumeroPlacasImplicado(), reporte.getFechaSuceso(),
                reporte.getIdevidencia(), reporte.getIdConductor());
        return invocarServicioWeb("reportes/","POST", params);
    }

    public  static Responses getReportes(Conductor conductor){
        return  invocarServicioWeb("/reportes/"+conductor.getIdConductor(), "GET",null);
    }

    public static Responses getReporte(String idReporte){
        return  invocarServicioWeb("reportes/"+idReporte, "GET", null);
    }

    public static Responses getDictamenId(int folio){
        return  invocarServicioWeb("dictamen/"+folio, "GET", null);
    }

    public static Responses enviarEvidencias(Evidencia evidencia){
        String params = String.format("{fotoDerecha1:%s,fotoDerecha2:%s,fotoIzquierda1:%s,fotoIzquierda2:%s," +
                        "fotoFrontal1:%s,fotoFrontal2:%s,fotoTrasera1:%s,fotoTrasera2:%s}",
                evidencia.getFotoDerecha1(), evidencia.getFotoDerecha2(), evidencia.getFotoIzquierda1(),
                evidencia.getFotoIzquierda2(), evidencia.getFotoFrontal1(), evidencia.getFotoFrontal2(),
                evidencia.getFotoTrasera1(), evidencia.getFotoTrasera2());
        return invocarServicioWeb("evidencias/", "POST", params);
    }

    public static Responses getDictamenten(Reporte reporte){
        return invocarServicioWeb("dictamen/"+reporte.getIdreporte(), "GET", null);
    }

    private  static Responses invocarServicioWeb(String url, String tipoInvocacion, String parametros){
        HttpURLConnection c = null;
        URL u = null;
        Responses res = new Responses();

        try{
            if(tipoInvocacion.equalsIgnoreCase("GET")){
                u = new URL(BASE_URL+url+((parametros!=null)?parametros:""));
                c = (HttpURLConnection)u.openConnection();
                c.setRequestMethod(tipoInvocacion);
                c.setRequestProperty("Content-lenght", "0");
                c.setUseCaches(false);
                c.setConnectTimeout(CONNECT_TIMEOUT);
                c.setReadTimeout(READ_TIMEOUT);
                c.connect();
            }else {
                u = new URL(BASE_URL+url);
                c =(HttpURLConnection)u.openConnection();
                c.setRequestMethod(tipoInvocacion);
                c.setDoOutput(true);
                c.setConnectTimeout(CONNECT_TIMEOUT);
                c.setReadTimeout(READ_TIMEOUT);
                c.setRequestProperty("Content-Type", "application/json; utf-8");
                c.setRequestProperty("Accept", "application/json");
                c.connect();
                //Pasar parametros en el cuerpo del mensaje post , put  y delete
                /*DataOutputStream wr = new DataOutputStream(c.getOutputStream());
                wr.writeBytes(parametros);
                wr.flush();
                wr.close();*/
                try(OutputStream os = c.getOutputStream()) {
                    byte[] input = parametros.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }
            //-------------------------------------//
            res.setStatus(c.getResponseCode());
            if(res.getStatus()!=200 && res.getStatus()!=201){
                res.setError(true);
            }
            if(c.getInputStream()!= null){
               /* BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line+"\n");
                }
                br.close();
                res.setResult(sb.toString());*/
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(c.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    System.out.println(response.toString());
                    res.setResult(response.toString());
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            res.setError(true);
            res.setResult(e.getMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
            res.setError(true);
            res.setResult(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            res.setError(true);
            res.setResult(e.getMessage());
        } finally {
            if(c != null){
                c.disconnect();
            }
        }
        return res;
    }
}

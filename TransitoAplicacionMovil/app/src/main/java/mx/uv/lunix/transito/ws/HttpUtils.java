package mx.uv.lunix.transito.ws;



import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

import mx.uv.lunix.transito.ws.pojos.Aseguradora;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Evidencia;
import mx.uv.lunix.transito.ws.pojos.Reporte;
import mx.uv.lunix.transito.ws.pojos.Vehiculo;


public class HttpUtils {

    private static String BASE_URL = "http://transitodesapps.azurewebsites.net/api/";
    private static final Integer CONNECT_TIMEOUT = 10000; //MILISECONDS
    private static final Integer READ_TIMEOUT = 10000; //MILISECONDS


    public static Response login(String telefono, String contrasena) {
        //----CREAR PARÁMETROS PARA POST, PUT, DELETE----//
        String param = String.format("telefono:%s&password:%s", telefono, contrasena);
        return invocarServicioWeb("Conductors/Login","POST", param);
    }

    public static  Response registroConductor(Conductor conductor){
        String param  = String.format("{nombre:%s,apellidoPaterno:%s&apellioMaterno:%s,fechaNacimiento:%s,numeroLicencia:%s,telefono:%s,password:%s,conductorBitacoraAcceso:%s,reporte:%s,vehiculo:%s}", conductor.getNombre(),
                conductor.getApellidoPaterno(), conductor.getApellidoPaterno(), conductor.getFechaNacimiento(), conductor.getNumeroLicencia(), conductor.getTelefono(),
        conductor.getPassword());
        return invocarServicioWeb("Conductors", "POST", param);
    }

    public static Response registroVehiculo(Vehiculo vehiculo){
        String param = String.format("{placas:%s,marca:%s,modelo:%s,anio:%s,color:%s,idaseguradora:%s,polizaSeguro:%s,idconductor:%s,idaseguradoraNavigation:%s,idconductorNavigation:%s}",
                vehiculo.getPlacas(), vehiculo.getModelo(), vehiculo.getAnio(), vehiculo.getColor(), vehiculo.getIdaseguradora(),vehiculo.getPolizaSeguro(), vehiculo.getIdcoductor());
        return invocarServicioWeb("vehiculoes", "POST", param);
    }

    public static Response getVehiculos(Conductor conductor){
        return invocarServicioWeb("vehiculoes/" +conductor.getIdConductor(),"GET", null);
    }

    public static Response getVehiculo(String id){
        return invocarServicioWeb("vehiculoes/"+id, "GET", null);
    }

    public static Response registrarAseguradora(Aseguradora aseguradora){
        String param = String.format("{nomnbre:%s,reporte:%s,vehiculo:%s}", aseguradora.getNombre());
        return  invocarServicioWeb("aseguradoras", "POST", param);
    }

    public static Response crearReporte(Reporte reporte){
        String params = String.format("{longitud:%s,latiud:%s,lugar:%s, nombreImplicado:%s,aseguradoraImplicado:%s," +
                "numeroPolizaImplicado:%s,marcaImplicado:%s,modeloImplicado:%s," +
                "colorImplicado:%s,placaImplicado,fechaSuceso:%s,idevidencia:%s,idcconductor:%s}",
                reporte.getLongitud(), reporte.getLatitud(), reporte.getLugar(), reporte.getNombreImplicado(), reporte.getIdAAeguradora(),
                reporte.getNumeroPolizaImplicado(),reporte.getMarcaImplicado(),reporte.getModeloImplicado(),
                reporte.getColorImplicado(), reporte.getNumeroPlacasImplicado(), reporte.getFechaSuceso(),
                reporte.getIdevidencia(), reporte.getIdConductor());
        return invocarServicioWeb("reportes/","POST", params);
    }

    public  static  Response getReportes(Conductor conductor){
        return  invocarServicioWeb("/reportes/"+conductor.getIdConductor(), "GET",null);
    }

    public static Response getReporte(String idReporte){
        return  invocarServicioWeb("reportes/"+idReporte, "GET", null);
    }

    public static Response getDictamenId(int folio){
        return  invocarServicioWeb("dictamen/"+folio, "GET", null);
    }

    public static Response enviarEvidencias(Evidencia evidencia){
        String params = String.format("{fotoDerecha1:%s,fotoDerecha2:%s,fotoIzquierda1:%s,fotoIzquierda2:%s," +
                "fotoFrontal1:%s,fotoFrontal2:%s,fotoTrasera1:%s,fotoTrasera2:%s}",
                evidencia.getFotoDerecha1(), evidencia.getFotoDerecha2(), evidencia.getFotoIzquierda1(),
                evidencia.getFotoIzquierda2(), evidencia.getFotoFrontal1(), evidencia.getFotoFrontal2(),
                evidencia.getFotoTrasera1(), evidencia.getFotoTrasera2());
        return invocarServicioWeb("evidencias/", "POST", params);
    }

    public static Response getDictamenten(Reporte reporte){
        return invocarServicioWeb("dictamen/"+reporte.getIdreporte(), "GET", null);
    }

    private  static  Response invocarServicioWeb(String url, String tipoInvocacion, String parametros){
        HttpURLConnection c = null;
        URL u = null;
        Response  res = new Response();

        try{
            if(tipoInvocacion.compareToIgnoreCase("GET")==0){
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
                //Pasar parametros en el cuerpo del mensaje post , put  y delete
                DataOutputStream wr = new DataOutputStream(c.getOutputStream());
                wr.writeBytes(parametros);
                wr.flush();
                wr.close();
            }
            //-------------------------------------//
            res.setStatus(c.getResponseCode());
            if(res.getStatus()!=200 && res.getStatus()!=201){
                res.setError(true);
            }
            if(c.getInputStream()!= null){
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line+"\n");
                }
                br.close();
                res.setResult(sb.toString());
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

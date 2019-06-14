package mx.uv.lunix.transito.ws;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import mx.uv.lunix.transito.ws.pojos.Aseguradora;
import mx.uv.lunix.transito.ws.pojos.Conductor;
import mx.uv.lunix.transito.ws.pojos.Vehiculo;


public class HttpUtils {

    private static String BASE_URL = "http://192.168.100.5:8080/api/";
    private static final Integer CONNECT_TIMEOUT = 4000; //MILISECONDS
    private static final Integer READ_TIMEOUT = 10000; //MILISECONDS


    public static Response login(String telefono, String contrasena) {
        //----CREAR PARÁMETROS PARA POST, PUT, DELETE----//
        String param = String.format("{telefono=%s,contrasena=%s}", telefono, contrasena);
        return invocarServicioWeb("conductors","POST", param);
    }

    public static  Response registroConductor(Conductor conductor){
        String param  = String.format("{nombre:%s,apellidoPaterno:%s&apellioMaterno:%s,fechaNacimiento:%s,numeroLicencia:%s,telefono:%s,password:%s}", conductor.getNombre(),
                conductor.getApellidoPaterno(), conductor.getApellidoPaterno(), conductor.getFechaNacimiento(), conductor.getNumeroLicencia(), conductor.getTelefono(),
        conductor.getPassword());
        return invocarServicioWeb("conductors", "POST", param);
    }

    public static Response registroVehiculo(Vehiculo vehiculo){
        String param = String.format("{placas:%s,marca:%s,modelo:%s,anio:%s,color:%s,idaseguradora:%s,polizaSeguro:%s,idconductor:%s,idaseguradoraNavigation:%s,idconductorNavigation:%s}",
                vehiculo.getPlacas(), vehiculo.getModelo(), vehiculo.getAnio(), vehiculo.getColor(), vehiculo.getIdaseguradora(),vehiculo.getPolizaSeguro(), vehiculo.getIdcoductor());
        return invocarServicioWeb("vehiculoes", "POST", param);
    }

    private static Response registrarAseguradora(Aseguradora aseguradora){
        String param = String.format("{nomnbre:%s,reporte:%s,vehiculo:%s}", aseguradora.getNombre());
        return  invocarServicioWeb("aseguradoras", "POST", param);
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

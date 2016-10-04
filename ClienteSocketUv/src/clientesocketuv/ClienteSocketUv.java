/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocketuv;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Edgar1
 */
public class ClienteSocketUv {
public static int puerto=9090;
        //String ip="74.208.234.113";
        //ip nuve
       // String ip="192.168.0.12";
 public static        String ip="127.0.0.1";
    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) throws InterruptedException {
        while(true){
        	 try
             {           
                 Socket cliente = new Socket(ip,puerto);
                 BufferedReader entCliente=new BufferedReader(new InputStreamReader(cliente.getInputStream()));              
                 String cadena= entCliente.readLine();
                 System.out.println(cadena);
                 String[] datos = cadena.split(",");
                 String get= datos[0];
                 get=get.substring(2);
                 String host= datos[1];
                 String port= datos[2];
                 port=port.replace(" ","");        
                 int puertohttp=Integer.parseInt(port);
                 PrintWriter salCliente = new PrintWriter(cliente.getOutputStream()); 
                 ClienteSocketUv proceso = new ClienteSocketUv();
                 String resul= proceso.ConectaCliente(get,host,puertohttp);
                 salCliente.println(resul);            
                 salCliente.close();
                 entCliente.close();
                
             }
             catch(Exception e)
             {
                 System.out.println(e.getMessage());
             }
        	 finally {
        		 Thread.sleep(1000);
        	 }
        }
    }
    public String ConectaCliente(String get,String host, int puerto) throws IOException{
	 
	  String hostname = host;
      int port = puerto;
      String resul="";
      Socket socket = null;
      PrintWriter writer = null;
      BufferedReader reader = null;

      try {
          socket = new Socket(hostname, port);
          
          writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
          writer.println(get);
          writer.println("Host: " + hostname);
          writer.println("Accept: */*");
          writer.println("User-Agent: Java"); // Be honest.
          writer.println(""); // Important, else the server will expect that there's more into the request.
          writer.flush();
          try{
       	 
          	reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              for (String line; (line = reader.readLine()) != null;) {
            	  resul+=line+"\n";
              }
          }
          catch(IOException logOrIgnore){
          	 if (writer != null) { writer.close(); }
          }
          
      } finally {
          if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {} 
          if (writer != null) { writer.close(); }
          if (socket != null) try { socket.close(); } catch (IOException logOrIgnore) {} 
      }
		
      return resul;
	}
    
}

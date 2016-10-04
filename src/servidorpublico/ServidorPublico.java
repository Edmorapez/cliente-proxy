/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorpublico;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Edgar1
 */
public class ServidorPublico {
 int puerto = 9191;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ServidorPublico proxy = new ServidorPublico();
      proxy.inicia();
    }
    
    public void inicia(){
        while(true){
                    try {
			ServerSocket s = new ServerSocket(puerto);
				System.out.println("Esperando conexion del navegador...");
				Socket entrante = s.accept();
				ProcesoNavegador pNavegador= new ProcesoNavegador(entrante);
				pNavegador.run();
				s.close();
				} catch (Exception e) {
			System.out.println(e.getMessage());
			}
                  }           
    }
    
}

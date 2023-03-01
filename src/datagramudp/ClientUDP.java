
package datagramudp;

import java.io.*;
import java.net.*;


public class ClientUDP {


    public static void main(String[] args) throws IOException{
        
      /*  
        DatagramSocket client = new DatagramSocket();
        InetAddress hostname = InetAddress.getByName("localhost");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String messaggio = in.readLine();
        byte[] buf = messaggio.getBytes();
        
        DatagramPacket p = new DatagramPacket(buf, buf.length, hostname, 8080);
        
        client.send(p);
        
       */ 
        
        
        
        int porta = 8080;
        InetAddress IPServer = InetAddress.getByName("localhost");
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];
        boolean connesso = true;
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        DatagramSocket clientSocket = new DatagramSocket();
        System.out.println("Client pronto ad inviare il messaggio....");
        
        
        while(connesso){
            
            String daSpedire = in.readLine();
            bufferOUT = daSpedire.getBytes();
            DatagramPacket sendPacket = new DatagramPacket (bufferOUT, bufferOUT.length , IPServer, porta);
            clientSocket.send(sendPacket);
            
            if(daSpedire.equals("fine")){
                connesso = false;
                System.out.println("Connessione terminata....");
            }

        }
        
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        clientSocket.receive(receivePacket);
        String ricevuto = new String(receivePacket.getData());
        
    }
    
}


package datagramudp;

import java.io.*;
import java.net.*;

public class ServerUDP {

    //capire come farlo con la OOP
    
 
    public static void main(String[] args) throws IOException{
        
        /*
        DatagramSocket server = new DatagramSocket(8080);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        server.receive(packet);
        String risposta = new String(packet.getData());
        System.out.println("Risposta : " + risposta);
        server.close();
        */
        
        
        
        int porta = 8080;
        DatagramSocket serverSocket = new DatagramSocket(porta);
        
        boolean attivo = true;
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];
        
        System.out.println("Server Attivo...");
        
        //metto in ascolto il sever finche non si connette un client
        while(attivo){
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
            serverSocket.receive(receivePacket);
            
            //leggo le informazioni del pacchetto che mi è arrivato 
            String ricevuto = new String(receivePacket.getData());
            int numCaratteri = receivePacket.getLength();
            
            //subString legge la stringa partendo da un index che gli passo io, in questo caso inizia da 0 fino alla lunghezza del messaggio
            ricevuto = ricevuto.substring(0,numCaratteri);
            System.out.println("Ricevuto : " + ricevuto);
            
            InetAddress IPClient = receivePacket.getAddress();
            int portaClinet = receivePacket.getPort();
            
            
            String daSpedire = ricevuto.toUpperCase();
            bufferOUT = daSpedire.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient, portaClinet);
            serverSocket.send(sendPacket); //il server risponde
            
            if(ricevuto.equals("fine")){
                System.out.println("Chiusura della connessione...");
                attivo=false;
            }
        }
        
        serverSocket.close();
            
        
        
        
    }
    
}

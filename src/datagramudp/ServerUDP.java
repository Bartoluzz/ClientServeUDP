
package datagramudp;

import java.io.*;
import java.net.*;


public class ServerUDP {

    //capire come farlo con la OOP
    public void start(){
        try {
            int porta = 8080;
            byte[] bufferIN = new byte[1024];
            byte[] bufferOUT = new byte[1024];
            boolean attivo = true;
            
            DatagramSocket serverSocket = new DatagramSocket(porta);
            System.out.println("[1]. Server attivo sulla porta " + porta);
            
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, 0, bufferIN.length);
            
            while(attivo == true){
                
                //ricevo messaggi dal client
                serverSocket.receive(receivePacket);
                int numChar = receivePacket.getLength();
                String ricevuto = new String(receivePacket.getData());
                ricevuto = ricevuto.substring(0,numChar);
                System.out.println("Ricevuto : " + ricevuto);
                                
                if(ricevuto.equals("fine")){
                    attivo = false;
                    System.out.println("Richeista di finire la connessione");
                }
                
                
                //ricavo le info del client
                InetAddress IPClient = receivePacket.getAddress();
                int portaClient = receivePacket.getPort();
                 
                //spedisco messaggi al client
                String daSpedire = ricevuto;
                bufferOUT = daSpedire.getBytes();
                DatagramPacket sendPakcet = new DatagramPacket(bufferOUT,0,bufferOUT.length, IPClient,portaClient);
                serverSocket.send(sendPakcet);
            }
            
            serverSocket.close();
            
        } catch (SocketException err){
            System.err.println("Errore nell'istanziare il socket");
        } catch (IOException err){
            System.err.println("Errore I/O");
        }
    }
 
    public static void main(String[] args) throws IOException{
        
             ServerUDP server = new ServerUDP();
             server.start();
        
    }
}   
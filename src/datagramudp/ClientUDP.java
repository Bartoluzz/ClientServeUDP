
package datagramudp;

import java.io.*;
import java.net.*;



public class ClientUDP {
    
      
    public void comunica(){
        try {
            int porta = 8080;
            InetAddress serverIP = InetAddress.getByName("localhost");
            byte[] bufferIN = new byte[1024];
            byte[] bufferOUT = new byte[1024];
            boolean attivo = true;
            
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("[1]. Client pronto ad inviare i messaggi : ");
            
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, 0, bufferIN.length);            
            
            while(attivo == true){
                
                //permette di inviare messaggi al server 
                String daSpedire = in.readLine();
                bufferOUT = daSpedire.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(bufferOUT, 0,bufferOUT.length,serverIP,porta);
                clientSocket.send(sendPacket);
                
                
                //serve per far intercettare i messaggi del server 
                clientSocket.receive(receivePacket);
                int numChar = receivePacket.getLength();
                String ricevuto = new String(receivePacket.getData());
                ricevuto = ricevuto.substring(0,numChar);
                System.out.println("Risposta del server : " + ricevuto.toUpperCase());
                
                
                if(daSpedire.equals("fine")){
                    attivo = false;
                    System.out.println("Richeista di finire la connessione");
                }
                

                
            }
            
            clientSocket.close();
            
            
        } catch (UnknownHostException err) {
            System.err.println("Errore DNS");
        } catch (SocketException ex) {
            System.err.println("Erroro nell'istanziamento del socket");
        } catch (IOException ex) {
            System.err.println("Erroro ");
        }
    }


    public static void main(String[] args) {
        
        ClientUDP client = new ClientUDP();
        client.comunica();

    }
    
}
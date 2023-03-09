package datagramudp;

import java.io.*;
import java.net.*;

public class ServerUDP {

    private DatagramSocket serverSocket;
    private int porta = 8080;
    private byte[] bufferIN = new byte[1024];
    private InetAddress clientIP;
    private int portaClient;

    public ServerUDP() throws SocketException {
        try {
            this.serverSocket = new DatagramSocket(porta);
            System.out.println("Server Attivo");
        } catch (SocketException ex) {
            System.out.println("Errore nell'avvio del server: " + ex.getMessage());
            throw ex;
        }
    }

    public String ricezione() throws IOException {

        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        this.serverSocket.receive(receivePacket);

        clientIP = receivePacket.getAddress();
        portaClient = receivePacket.getPort();
        String ricevuto = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Ricevuto : " + ricevuto);

        return ricevuto;
    }

    public void invio() throws IOException {

        String daSpedire = ricezione();

        if (daSpedire.equals("fine")) {
            this.serverSocket.close();
            return;
        }

        byte[] bufferOUT = daSpedire.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, clientIP, portaClient);
        this.serverSocket.send(sendPacket);

    }

    public static void main(String[] args) {

        try {
            ServerUDP server = new ServerUDP();

            while (true) {
                server.invio();
            }
        } catch (IOException ex) {
            System.out.println("Chiusura del server " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Errore" + ex.getMessage());
        }

    }
}

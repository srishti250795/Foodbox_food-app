package com.mycompany.fds;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class IotLocalisation implements Runnable {
    static String st ="";

    @Override
    public void run() {
        File file = new File("src/main/resources/iotFiles/metreSensor");
        BufferedReader br = null;


        int port = 2000;
        ServerSocket sersoc = null;
        try {
            br = new BufferedReader(new FileReader(file));
            sersoc = new ServerSocket(port);

            Socket soc = sersoc.accept();
            OutputStream flux = soc.getOutputStream() ;
            OutputStreamWriter sortie = new OutputStreamWriter (flux) ;
            while (true) {
                st = br.readLine();
                sortie.write(st+"\n") ;
                sortie.flush(); // pour forcer l'envoi de la ligne
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

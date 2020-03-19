package Ex2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connexion implements Runnable {

    private Socket soc;

    public Connexion(Socket soc) {
        this.soc = soc;
    }

    public void run() {

        try {
            BufferedReader ins = new BufferedReader(
                    new InputStreamReader(soc.getInputStream()));
            PrintWriter outs = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(soc.getOutputStream())), true);

            while(true) {
                String strIN = ins.readLine();
                if(strIN.equals("stop")) break;

                System.out.println(strIN);
                String strOUT = "Reçu:" + strIN;
                outs.println(strOUT);
            }
            System.out.println("stop");
            outs.println("stop");

            ins.close();
            outs.close();
        }catch (java.io.IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                soc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

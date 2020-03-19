import java.net.*;
import java.io.*;

public class Serveur {
    public static void main(String[] args) throws Exception {

        ServerSocket s = new ServerSocket(6020);
        System.out.println("START");
        System.out.println(s.getInetAddress());

        Socket soc = s.accept();
        System.out.println("Client Connecté");
        BufferedReader ins = new BufferedReader(
                new InputStreamReader(soc.getInputStream()));
        PrintWriter outs = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(soc.getOutputStream())), true);

        // insertion de la boucle du serveur ici
        while(true) {
            String strIN = ins.readLine();
            if(strIN.equals("stop")) break;

            System.out.println(strIN);
            String strOUT = "Reçu:" + strIN;
            outs.println(strOUT);
        }

        ins.close();
        outs.close();
        soc.close();
    }
}

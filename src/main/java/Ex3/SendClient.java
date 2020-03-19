package Ex3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendClient implements Runnable {

    boolean running = true;

    Socket soc;

    public SendClient(Socket soc) {
        this.soc = soc;
    }

    public void run() {

        try {
            Scanner scan = new Scanner(System.in);
            PrintWriter outs = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);

            //input nom
            outs.println(scan.nextLine());

            System.out.println("Pour avoir une liste des commandes tapez /help");
            System.out.println("Vous êtes dans le salon d'attente, pour choisir un salon tapez /changeSalon nomduSalon");
            System.out.println("pour avoir la liste des salons tapez /salons");

            while (running) {
                String msg = scan.nextLine();
                if (msg.equals("/deconnect")) {
                    this.running = false;
                    break;
                }
                outs.println(msg);
            }
            System.out.println("deconnexion");
            outs.println("/deconnect");

            outs.close();
        }catch (java.io.IOException e){
            e.printStackTrace();
        }
    }

    boolean isRunning(){
        return running;
    }
}

package Ex3;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Serveur {

    public static void main(String[] args) throws Exception {

        ServerSocket s = new ServerSocket(6020);
        System.out.println("START");

        Salons salons = new Salons();
        try {
            ExecutorService executor = Executors.newFixedThreadPool(10);

            // insertion de la boucle du serveur ici
            while (true) {
                Socket soc = s.accept();
                Connexion connexion = new Connexion(soc,salons);

                //j'ajoute la connexion au salon d'attente
                salons.getSalons().get(0).addToSalon(connexion);
                executor.execute(connexion);
            }

        }finally {
            s.close();
        }

    }
}

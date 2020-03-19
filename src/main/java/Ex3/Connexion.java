package Ex3;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Connexion implements Runnable {

    private Socket soc;

    private Salons salons;

    private Salon salonActuel;

    private String nom;

    private BufferedReader ins;

    private PrintWriter outs;

    public Connexion(Socket soc, Salons salons) {
        this.soc = soc;
        this.salons = salons;
        this.salonActuel = salons.getSalon("attente");
        try {
            this.ins = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            this.outs = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
        }catch (java.io.IOException e){
            e.printStackTrace();
        }
        this.nom = "";
    }

    public void run() {
        try {
            String name;

            //on verifie l'unicité des noms ou le fait qu'il y n'y ait pas d'espace dans le nom
            do {
                outs.println("Nom des utilisateurs présents: " + salons.getAllConnectesNames());
                outs.println("Veuillez entrer votre nom (sans espaces):");
                name = ins.readLine();
            }while (salons.getListAllConnectesNames().contains(name) || name.contains(" ") || name.equals(""));

            this.nom = name;


            while(true) {
                String strIN = ins.readLine();
                if(strIN.equals("stop")) break;


                //le switch  avec String ne marchait pas
                if(strIN.equals("/help")) {

                    this.outs.println("/help ; /deconnect ; /salons ; /etatSalons ; /salonActuel ; /changeSalon nomSalon ; /users ; /whisper nomPersonne message");

                }else if(strIN.equals("/deconnect")){

                    ins.close();
                    outs.close();
                    soc.close();

                }else if(strIN.equals("/salons")) {

                    this.outs.println(salons.getSalonsNames());

                }else if(strIN.equals("/etatSalons")){
                    this.outs.println(salons.getAllEtats());

                }else if(strIN.equals("/salonActuel")){

                    this.outs.println("Vous êtes dans le salon " + salonActuel.getNom());

                }else if(strIN.split(" ")[0].equals("/changeSalon")) {

                    this.salonActuel = salons.changeSalon(this, this.salonActuel, strIN.split(" ")[1]);
                    this.outs.println("Vous êtes maintenant dans le salon " + this.salonActuel.getNom());

                }else if(strIN.equals("/users")) {

                    this.outs.println(salons.getAllConnectesNames());

                }else if(strIN.split(" ")[0].equals("/whisper")){
                    Connexion whisperingTo = salons.findUser(strIN.split(" ")[1]);
                    if (whisperingTo == null){
                        this.outs.println("Ce nom d'utilisateur n'est pas bon");
                    }else{
                        String[] str = strIN.split(" ",3);

                        whisperingTo.outs.println("||whisper from " + this.nom + ": " + str[2]);
                        this.outs.println("||whispering to " + str[1] + ": " + str[2]);

                    }

                }else{

                    System.out.println(strIN);
                    for (Connexion c: salonActuel.getConnectes()) {
                        c.outs.println(nom + ":" + strIN);
                    }

                }
                strIN = "";
            }


            System.out.println("stop");
            outs.println("stop");
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            salonActuel.removeFromSalon(this);
            try {
                soc.close();
                ins.close();
                outs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getNom() {
        return nom;
    }
}

package Ex3;

import java.util.ArrayList;

public class Salon {

    private ArrayList<Connexion> connectes;

    private String nom;

    public Salon(String nom) {
        this.nom = nom;
        this.connectes = new ArrayList<Connexion>();
    }

    public void addToSalon(Connexion c){
        connectes.add(c);
    }

    public void removeFromSalon(Connexion c){
        connectes.remove(c);
    }

    public String getEtatSalon(){
        String str;
        if (this.connectes.size() == 0){
            str = "";
        }
        else if (this.connectes.size() == 1){
           str = this.connectes.get(0).getNom();
        }
        else{
            str = "";
            for (Connexion c: this.connectes) {
                str += c.getNom() + " - ";
            }
            str = str.substring(0,str.length()-3);
        }
        return str.equals("")? "Le salon " + this.nom + " est vide" : "Noms des connectés sur le channel " + this.nom + ": " + str;
    }


    public String getConnectesNames(){
        String str;
        if (this.connectes.size() == 0){
            str = "";
        }
        else if (this.connectes.size() == 1){
            str = this.connectes.get(0).getNom();
        }
        else{
            str = "";
            for (Connexion c: this.connectes) {
                if (c.getNom() != null && !c.getNom().equals("")){
                    str += c.getNom() + " - ";
                }
            }
            if (str.length() > 3) {
                str = str.substring(0, str.length() - 3);
            }
        }
        return str;
    }

    public ArrayList<String> getListConnectesNames(){
        ArrayList<String> names = new ArrayList<String>();
        for (Connexion c: connectes) {
            if (c.getNom() != null && !c.getNom().equals("")){
                names.add(c.getNom());
            }
        }
        return names;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Connexion> getConnectes() {
        return connectes;
    }
}

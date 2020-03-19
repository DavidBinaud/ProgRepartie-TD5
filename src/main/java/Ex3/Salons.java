package Ex3;

import java.util.ArrayList;

public class Salons {

    private ArrayList<Salon> salons;

    public Salons() {
        this.salons = new ArrayList<Salon>();
        this.salons.add(new Salon("attente"));
        this.salons.add(new Salon("chatroom-cours"));
        this.salons.add(new Salon("chatroom-détente"));
    }

    public String getSalonsNames(){
        String str = "";

        for (Salon s: this.salons) {
            str += s.getNom() + " - ";
        }
        str = str.substring(0,str.length()-3);

        return "Noms des salons: " + str;
    }

    public String getAllConnectesNames(){
        String str = "";
        for (Salon s: this.salons) {
            String names = s.getConnectesNames();
            if (names != null && !names.equals("")) {
                str += s.getConnectesNames() + "; ";
            }
        }
        return str;
    }

    public ArrayList<String> getListAllConnectesNames(){
        ArrayList<String> names = new ArrayList<String>();
        for (Salon s: this.salons) {
            names.addAll(s.getListConnectesNames());
        }
        return names;
    }

    public String getAllEtats(){
        String str = "";
        for (Salon s: this.salons) {
            str += s.getEtatSalon() + "; ";
        }
        return str;
    }

    public Salon getSalon(String s){
        Salon salon = this.salons.get(0);

        for (Salon room: this.salons) {
            if(room.getNom().equals(s)){
                salon = room;
            }
        }
        return salon;
    }

    public Salon changeSalon(Connexion c,Salon actuel,String arrivée){
        actuel.removeFromSalon(c);
        getSalon(arrivée).addToSalon(c);
        return getSalon(arrivée);
    }

    public Connexion findUser(String s){
        Connexion user = null;
        int i = 0;
        while(user == null && i < salons.size()){
            for (Connexion c: this.salons.get(i).getConnectes()) {
                if(c.getNom().equals(s)){
                    user = c;
                }
            }
            i++;
        }
        return user;
    }

    public ArrayList<Salon> getSalons() {
        return salons;
    }
}

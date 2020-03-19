package Ex1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientQ5 {

    public static void main(String[] args) throws Exception{

        Socket soc = new Socket(args[0],6020);
        System.out.println("START");

        Scanner scan = new Scanner(System.in);

        BufferedReader ins = new BufferedReader(
                new InputStreamReader(soc.getInputStream()));
        PrintWriter outs = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(soc.getOutputStream())), true);

        for (int i = 1; i <= 10; i++) {
            outs.println("Message " + i);
            System.out.println(ins.readLine());
        }
        System.out.println("stop");
        outs.println("stop");



        ins.close();
        outs.close();
        soc.close();
    }
}

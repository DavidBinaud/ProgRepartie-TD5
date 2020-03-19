package Ex3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) throws Exception{

        Socket soc = new Socket(args[0],6020);
        System.out.println("START");
        System.out.println(soc);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        BufferedReader ins = new BufferedReader( new InputStreamReader(soc.getInputStream()));

        System.out.println(ins.readLine());
        //demande d'input nom
        System.out.println(ins.readLine());


        SendClient send = new SendClient(soc);
        executor.execute(send);





        while (send.isRunning()) {
            System.out.println(ins.readLine());
        }


        ins.close();
        soc.close();
    }
}

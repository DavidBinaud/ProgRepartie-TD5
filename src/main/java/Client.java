import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception{

        Socket soc = new Socket(args[0],6020);
        System.out.println("START");

        Scanner scan = new Scanner(System.in);

        BufferedReader ins = new BufferedReader(
                new InputStreamReader(soc.getInputStream()));
        PrintWriter outs = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(soc.getOutputStream())), true);

        while (true) {
            String msg = scan.nextLine();
            if(msg.equals("stop")){break;}
            outs.println(msg);
            System.out.println(ins.readLine());
        }
        System.out.println("stop");
        outs.println("stop");



        ins.close();
        outs.close();
        soc.close();
    }
}

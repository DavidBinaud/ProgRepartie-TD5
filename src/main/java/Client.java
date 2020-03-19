import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception{

        Socket soc = new Socket(args[0],6020);
        System.out.println("START");

        BufferedReader ins = new BufferedReader(
                new InputStreamReader(soc.getInputStream()));
        PrintWriter outs = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(soc.getOutputStream())), true);

        for (int i = 1; i <= 10; i++) {
            outs.println("Message " + i);
            System.out.println(ins.readLine());
        }
        System.out.println("END");
        outs.println("END");

        ins.close();
        outs.close();
        soc.close();
    }
}

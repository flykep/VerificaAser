package compito.aser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket clientSocket = new Socket("localhost", 3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Messaggio di benvenuto
        System.out.println(in.readLine());

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            String scelta = keyboard.nextLine();
            out.println(scelta);
            
           
           
            System.out.println(in.readLine());

            
            if (in.readLine() == "OK CORRECT"  || in.readLine() == "QUIT"){
                System.out.println(in.readLine());
                break;
            }
            

        }

    }
}
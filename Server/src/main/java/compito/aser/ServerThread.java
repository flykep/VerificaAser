package compito.aser;

import java.util.concurrent.ThreadLocalRandom;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private int min = 1;
    private int max = 100;

    private int segreto = ThreadLocalRandom.current().nextInt(min, max + 1),
            tries = 0;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("WELCOME INDOVINA v1 RANGE 1 100");

            while (true) {
                String[] parts = in.readLine().split(" ");

                if (parts[0].equals("GUESS")) {

                    int number = Integer.parseInt(parts[1]);

                    if (number > max && number < min) {
                        out.println("ERR OUTOFRANGE 1 100");
                        break;
                    } 
                    if (number == segreto) {
                        out.println("OK CORRECT");
                        break;
                    } else if (number > segreto) {
                        out.println("HINT LOWER");
                    } else if (number < segreto) {
                        System.out.println(number);
                        out.println("HINT HIGHER");
                    }
                    tries++;
                }

                else if (parts[0].equals("RANGE")) {
                    if (tries >= 1) {
                        min = Integer.parseInt(parts[1]);
                        max = Integer.parseInt(parts[2]);
                        segreto = ThreadLocalRandom.current().nextInt(min, max + 1);
                    }

                    else {
                        out.println("ERR NOTALLOWED");
                    }
                }

                else if (parts[0].equals("NEW")) {
                    out.println("WELCOME INDOVINA v1 RANGE 1 100");

                    tries = 0;

                    segreto = ThreadLocalRandom.current().nextInt(1, 101);
                }

                else if (parts[0].equals("STATS"))
                    out.println("INFO RANGE " + min + " " + max + " TRIES : " + tries);

                else if (parts[0].equals("QUIT")) {
                    out.println("BYE");
                    break;
                }

                else {
                    out.println("ERR UNKNOWNCMD");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

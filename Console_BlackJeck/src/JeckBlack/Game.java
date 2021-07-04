/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 * @author Vitalik
 */
public class Game {

    public static void Run() throws IOException, URISyntaxException {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        BufferedReader im = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader ing = new BufferedReader(new InputStreamReader(System.in));
        long time = System.currentTimeMillis();
        boolean run = true;
        Player player = new Player();
        Table t = new Table();
        out.print("---> ");
       BufferedInputStream bufferedInputStream = new BufferedInputStream(System.in);
                byte[] bytes = bufferedInputStream.readAllBytes();
                       player.Name = bytesToStringUTFNIO(bytes);
        Player.balance = 1000;

        while (run) {

            RUN:
            while (true) {
                out.print("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                        + "╏                                  Зробити ставку(1), вийти із гри(0):                                       ╏\n"
                        + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛\n"
                        + "---> ");
                String input = im.readLine();
                switch (input) {
                    case "0":
                        time = System.currentTimeMillis() - time;
                            Info.Writer(player.Name, Player.balance, time);
                        Main.Menu();
                        break;
                    case "1":
                        break RUN;
                    default:
                        out.println("Некоректний ввід");
                        break;
                }
            }

            String st = "";
            while (true) {
                Z(Player.balance);
                try {
                    Player.stavka = 0;
                    out.print("╏                                          Зробіть ставку                                                    ╏\n"
                            + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛\n"
                            + "---> ");
                    st = im.readLine();
                    Player.stavka = Integer.parseInt(st);
                } catch (NumberFormatException ex) {
                    out.println("Некоректний ввід");
                    continue;
                }

                if (Player.stavka > 0 && Player.stavka <= Player.balance) {
                    break;

                }
                out.println("Некоректний ввід");
            }

            player.firstCard();

            if (t.sum(player.card1) + t.sum(player.card2) == 21) {
                out.println("У вас BlackJаck");
                Dealer.Border(Player.NumPlayer);
                Player.balance += Player.stavka + Player.stavka / 2;

            } else {
                Dealer.Border(Player.NumPlayer);
                Dealer d = new Dealer();
            }
            if (Player.balance <= 0) {
                out.println("Вітаю! Ти просадив все бабло ");
                run = false;
                String s = im.readLine();
            }
        }
    }

    public static void Z(int y) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        String v = "╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕";
        String a = "╏                                На разі ваш баланс становить: " + y + "$"+"                                   ";
        
        while(true){
            if(a.length() == 109){
            break;
            }
            a += " ";
        }
        String m = "\n" + v + "\n" + a + "╏";
        out.println(m);
    }
    public static String bytesToStringUTFNIO(byte[] bytes) {

        return ByteBuffer.wrap(bytes).asCharBuffer().toString();

    }
    
}

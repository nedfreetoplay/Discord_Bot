import sx.blah.discord.api.IDiscordClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static String Token = "";

    public static void main(String[] args){

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(Token);

        cli.getDispatcher().registerListener(new MyEvents());
        BotUtils.Names.add("Братик");

        cli.login();

    }

    public static String[] readFileToLine(String fileName) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            while (reader.readLine() != null) {
                lines.add(reader.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

        return lines.toArray(new String[lines.size()]);
    }

    /*public static void writeFileLine(String fileName) throws FileNotFoundException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    }*/

}
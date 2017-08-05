
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Commands {

    public static ArrayList<String> Jokes = new ArrayList<String>();

    protected static void Help(IChannel ch){
        EmbedBuilder c = new EmbedBuilder();
        c.withTitle("Список доспутных команд:");

        //IGuild permission = event.getAuthor().addRole();
        for (String x:BotUtils.commands) {
            c.appendDesc("\n" + BotUtils.BOT_PREFIX + x);
        }
        BotUtils.sendMessage(ch, c.build());
        String[] test = "DDDDD".split("%");
        System.out.println(test.length);
    }

    protected static void Send(IChannel ch, IUser user, String text){
        if (text == null) {
            BotUtils.CommandError(ch, user);
        } else {
            EmbedBuilder c = new EmbedBuilder();
            c.withAuthorIcon(user.getAvatarURL());
            c.withAuthorName(user.getName());
            c.withTitle(text);
            c.withImage("https://vk.com/doc371092959_448486027");
            BotUtils.sendMessage(ch, c.build());
        }
    }

    /*protected static void AddJoke(MessageReceivedEvent event){
        Jokes.add(event.getMessage().getContent());
    }*/

    protected static void Joke(IChannel ch, IUser user, int index) throws NullPointerException {
        if (Jokes.isEmpty()){
            String[] Joke;
            try{
                Joke = Main.readFileToLine("C:\\games\\data\\Анекдоты.txt");
                for (String x:Joke) {
                    Jokes.add(x);
                }
            } catch(FileNotFoundException e) {
                System.err.println("Error: " + e);
            }
        }
        EmbedBuilder c = new EmbedBuilder();
        int Index = index >= 0? index : (int)Math.round(Math.random() * Jokes.size());
        System.out.println("Index = " + Index);//NullPointerExeption
        String joke;
        System.out.println(Jokes.get(Index));
        joke = Jokes.get(Index);
        System.out.println(joke);
        String[] joke1 = joke.split("%");
        for (int i = 0; i < joke1.length; i++){
            System.out.println(i + " " + joke1[i]);
        }
        System.out.println();
        c.withTitle("Анекдот");
        for (String x:joke1) {
            c.appendDesc("\n" + x);
        }
        BotUtils.sendMessage(ch, c.build());
    }

    protected static void Joke(IChannel ch, IUser user) {
        Joke(ch, user, -1);
    }

    protected static void Name(IChannel ch, IUser user, String type, String text){
        if ( type == null | text == null)
            BotUtils.CommandError(ch, user);
        else {
            EmbedBuilder m = new EmbedBuilder();
            switch (type){
                case "add":
                    break;
                case "remove":
                    for (String i: BotUtils.Names) {
                        if (text.startsWith(i)){
                        }
                    }
                    break;
                default:
                    BotUtils.CommandError(ch, user);
            }
        }
    }
}

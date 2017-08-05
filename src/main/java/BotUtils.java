import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class BotUtils {

    // Constants for use throughout the bot
    static String BOT_PREFIX = "!";
    static String[] commands = {"Help", "Send", "Joke"};
    static List<String> Names = new ArrayList<String>();

    // Handles the creation and getting of a IDiscordClient object for a token
    static IDiscordClient getBuiltDiscordClient(String token){

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .build();

    }

    static void sendMessage(IChannel channel, String message){

        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }

    static void sendMessage(IChannel channel, EmbedObject message){

        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });

    }

    static void CommandError(IChannel channel, IUser user){
        EmbedBuilder err = new EmbedBuilder();
        err.withAuthorName(user.getName());
        err.withAuthorIcon(user.getAvatarURL());
        //err.withImage("http://abload.de/img/14127546979710aip3r.jpg");
        err.withImage("https://4.downloader.disk.yandex.ru/disk/425a3fc84e8f44410d264115eb86655ad0c876eb6334e21602c1deeab73807b5/59859b18/acGpgkvU459Q208tNc5Lr4C4rtmHQJ_TXE_PVs-5YORJ8hOLUPHPdiD93O0KK54w84JLuNFvsSRhub2hRrVf5w%3D%3D?uid=0&filename=14127546979710aip3r.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&fsize=34296&hid=bed353dfa05de7a8baf3b8fa57c7f6d9&media_type=image&tknv=v2&etag=44ecf8ba82aa15265d4d85a625f4216b");
        sendMessage(channel, err.build());
    }
}

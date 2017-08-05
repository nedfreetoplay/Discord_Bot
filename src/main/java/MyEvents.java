import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

public class MyEvents {
    MessageReceivedEvent oldEvent;

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event){
        if (oldEvent != null) {
            oldEvent.getMessage().delete();
        }
        IMessage message = event.getMessage();
        String command = message.getContent().toString().substring(1).toLowerCase();
        EmbedBuilder eb = new EmbedBuilder();

        if (message.getContent().startsWith(BotUtils.BOT_PREFIX)){
            String[] args = command.split(" ");
            switch(args[0]){
                case "help":
                    Commands.Help(message.getChannel());
                    break;
                case "send":
                    if (args.length > 1) {
                        BotUtils.CommandError(message.getChannel(), message.getAuthor());
                        Commands.Send(message.getChannel(), message.getAuthor(), args[1]);
                    } else
                        Commands.Send(message.getChannel(), message.getAuthor(), null);
                    break;
                case "joke":
                    if (args.length > 1)
                        try{
                            Commands.Joke(message.getChannel(), message.getAuthor(), Integer.parseInt(args[1]));
                        } catch (NumberFormatException e){
                            BotUtils.sendMessage(event.getChannel(), event.getAuthor().getName() +
                                    args[1] + " этот параметр не является целочисленным!\n" +
                                    BotUtils.BOT_PREFIX + "Joke [Index]");
                        }
                    else
                        Commands.Joke(message.getChannel(), message.getAuthor());
                    break;
                default:
                    eb.withTitle(BotUtils.Names.get((int)(Math.random()*BotUtils.Names.size())) + ", ты ошибся в регистре команды: " +
                            BotUtils.BOT_PREFIX + command);
                    eb.withDesc("\nПопробуй использовать команду " + BotUtils.BOT_PREFIX + "Help" +
                            " для вызова списка команд.");

                    BotUtils.sendMessage(event.getChannel(), eb.build());
            }
            oldEvent = event;
            message.delete();
        }
    }

}
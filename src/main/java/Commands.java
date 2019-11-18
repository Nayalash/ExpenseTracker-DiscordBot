import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileWriter;
import java.io.IOException;

public class Commands extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;


        Message message = event.getMessage();

        String content = message.getContentRaw();


        if (content.equals("!help")) {

            MessageChannel channel = event.getChannel();

            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("Commands \n \n");
            help.setAuthor("Expense Bot");
            help.addField("///", "///", false);
            help.addField("///", "///", false);
            help.addField("///", "///", false);
            help.addField("Creator", "Nayalash Mohammad \n https://github.com/Nayalash", false);
            help.setDescription("///");
            help.setColor(0xf45642);
            channel.sendTyping().queue();
            channel.sendMessage(help.build()).queue();
            help.clear();

        }

        String rawMessage[] = event.getMessage().getContentRaw().split(" ");

        if(rawMessage[0].equals("!add")) {

            try {
                FileWriter writer = new FileWriter("prices.txt", true);
                writer.append(rawMessage[2]+ ",");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            MessageChannel channel = event.getChannel();

            EmbedBuilder help = new EmbedBuilder();
            help.setTitle(rawMessage[1]);
            help.setAuthor(rawMessage[2]);
            help.addField("Description", rawMessage[3], false);
            help.setColor(0xf45642);
            channel.sendTyping().queue();
            channel.sendMessage(help.build()).queue();
            help.clear();
        }

        else if (content.equals("!total")) {


            MessageChannel channel = event.getChannel();

            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("");
            channel.sendTyping().queue();
            channel.sendMessage(help.build()).queue();
            help.clear();


        }

    }


}

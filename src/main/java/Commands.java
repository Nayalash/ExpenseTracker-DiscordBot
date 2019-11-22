import data.Expense;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        String[] rawMessage = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();

        if(rawMessage[0].equals("!add")) {

            try {
                FileWriter writer = new FileWriter("prices.txt", true);
                writer.append(rawMessage[1] + ":" + rawMessage[2] + ":" + rawMessage[3] + ",");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            EmbedBuilder help = new EmbedBuilder();
            help.setTitle(rawMessage[1]);
            help.setAuthor(rawMessage[2]);
            help.addField("Description", rawMessage[3], false);
            help.setColor(0xf45642);
            channel.sendTyping().queue();
            channel.sendMessage(help.build()).queue();
            help.clear();
        }

        else if (content.equals("!list")) {

            //loop through all entries
            //each entry, split using :. Entry 0 :, Entry lastIndex :, Entry finalIndex
            try {
                for(Expense expense : fetchExpenses()) {
                    channel.sendMessage("```Your Expense: " + expense.getName() + ", Your Price: " + expense.getPrice() + ", Your Description: " + expense.getDescription() + "```").complete();
                }
            } catch (IOException e) {e.printStackTrace();}
        }

        else if (content.equals("!total")){
            try {
                float total = fetchExpenses().stream()
                        .map(Expense::getPrice)
                        .reduce(Float::sum).get();
                channel.sendMessage("Your Total is: " + total).complete();
            } catch (IOException e) {e.printStackTrace();}
        }


        else if (content.equals("!remove")){
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("prices.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            writer.print("");
            writer.close();
        }

    }


    private List<Expense> fetchExpenses() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("prices.txt"));
        String line;
        StringBuilder data = new StringBuilder();

        while((line = reader.readLine()) != null) {
            data.append(line);
        }

        List<Expense> expenses = new ArrayList<>();

        for(String rawEntry : data.toString().split(",")) {
            String item = rawEntry.substring(0, rawEntry.indexOf(":"));
            String price = rawEntry.substring(rawEntry.indexOf(":") + 1, rawEntry.lastIndexOf(":"));
            String description = rawEntry.substring(rawEntry.lastIndexOf(":") + 1);
            expenses.add(new Expense(item, Float.parseFloat(price), description));
        }

        return expenses;
    }


}

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Bot {

    private static JDA bot;
    private static String token;

    public static void main(String[] args) throws LoginException, FileNotFoundException, IOException {

        FileReader reader = new FileReader("token.txt");
        char[] tokenData = new char[59];

        int response = reader.read(tokenData);
        token = new String(tokenData);

        bot = new JDABuilder(token).build();
        bot.getPresence().setStatus(OnlineStatus.ONLINE);
        bot.getPresence().setActivity(Activity.listening("Expenses"));

        bot.addEventListener(new Commands());

    }
}
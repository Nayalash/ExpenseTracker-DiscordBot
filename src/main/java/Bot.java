import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private static JDA bot;
    private static final String token = "";

    public static void main(String[] args) throws LoginException {

        bot = new JDABuilder(token).build();
        bot.getPresence().setStatus(OnlineStatus.ONLINE);
        bot.getPresence().setActivity(Activity.listening("Expenses"));

        bot.addEventListener(new Commands());

    }
}
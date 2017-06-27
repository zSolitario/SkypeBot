package me.arthurgui.bot;

import com.skype.Profile;
import com.skype.Skype;
import com.skype.SkypeException;
import me.arthurgui.bot.eventos.Add;
import me.arthurgui.bot.eventos.Chat;
import me.arthurgui.bot.eventos.Ligacoes;

/**
 * Created by Arthur on 27/06/2017.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Ligando bot...");
        Skype.setDaemon(false);
        try {
            if(Skype.isRunning()) {
                Skype.addCallMonitorListener(new Ligacoes());
                Skype.addChatMessageListener(new Chat());
                Skype.getProfile().setStatus(Profile.Status.ONLINE);
                Skype.getProfile().setMoodMessage("!help para ver as opções ^^");
            }else{
                System.out.println("Por favor instale o skype");
            }
        }catch (SkypeException e){
            e.printStackTrace();
        }
        new Add();
        System.out.println("Bot iniciado!");
        /*try{
            Skype.getProfile().setStatus(Profile.Status.OFFLINE);
            Skype.getProfile().setMoodMessage("Offline ;-;");
            System.out.println("Bot desligado!");
        }catch (SkypeException e){
            e.printStackTrace();
        }*/
    }

}

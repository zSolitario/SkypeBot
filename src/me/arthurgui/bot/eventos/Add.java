package me.arthurgui.bot.eventos;

import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arthur on 27/06/2017.
 */
public class Add extends TimerTask {
    private Timer timer;

    public Add(){
        new Thread(() ->{
            timer = new Timer();
            timer.scheduleAtFixedRate(this, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(40));
        }).start();
    }

    @Override
    public void run() {
        try{
            for(Friend f : Skype.getContactList().getAllUserWaitingForAuthorization()){
                f.setAuthorized(true);
                System.out.println("Pessoa adicionada: " + f.getDisplayName());
            }
        }catch (SkypeException e){
            e.printStackTrace();
        }
    }
}

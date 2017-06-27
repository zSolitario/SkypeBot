package me.arthurgui.bot.eventos;

import com.skype.Call;
import com.skype.CallMonitorListener;
import com.skype.SkypeException;

/**
 * Created by Arthur on 27/06/2017.
 */
public class Ligacoes implements CallMonitorListener {

    @Override
    public void callMonitor(Call call, Call.Status status) throws SkypeException {
        try{
            call.cancel();
            call.getPartner().send("Desculpe porém eu não atendo ligações ^^");
            call.getPartner().send("!help para ver os comandos.");
        }catch (Exception e){
            System.out.println("Ligação rejeitada (" + call.getPartnerDisplayName() + ")");
        }
    }
}

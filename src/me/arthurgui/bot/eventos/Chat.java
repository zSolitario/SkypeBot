package me.arthurgui.bot.eventos;

import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Arthur on 27/06/2017.
 */
public class Chat implements ChatMessageListener {

    @Override
    public void chatMessageReceived(ChatMessage chatMessage) throws SkypeException {
        System.out.println("["+ Calendar.HOUR + ":" + Calendar.MINUTE + ":" + Calendar.SECOND +"] " + chatMessage.getSender() + " -> " + chatMessage.getContent());
        if(chatMessage.getType() != ChatMessage.Type.SAID){
            return;
        }
        if(!chatMessage.getContent().startsWith("!")){
            chatMessage.getSender().send("/me !help para ver os comandos.");
            return;
        }
        if(chatMessage.getContent().startsWith("!help") || chatMessage.getContent().startsWith("!ajuda")){
            chatMessage.getSender().send("*!serverip <minecraft domain ip>" +
                    "!serverhost <numberic ip>*");
            return;
        }
        if(chatMessage.getContent().startsWith("!autor") || chatMessage.getContent().startsWith("!author")){
            chatMessage.getSender().send("/me <3 ArthurGUI <3");
            return;
        }
        if(chatMessage.getContent().startsWith("!versão") || chatMessage.getContent().startsWith("!version")){
            chatMessage.getSender().send("/me <3 v1.0 <3");
            return;
        }

        if(chatMessage.getContent().startsWith("!serverhost")){
            String[] args = chatMessage.getContent().replace("!serverhost", "").split(" ");
            if(args.length > 1){
                chatMessage.getSender().send("!serverhost <numeric ip>");
                return;
            }
            chatMessage.getSender().send("/me Verificando ...");
            try {
                InetAddress addr = InetAddress.getByName(args[0]);
                String host = addr.getCanonicalHostName();
                chatMessage.getSender().send("Host: " + host);
            }catch (UnknownHostException e){
                chatMessage.getSender().send("IP NO FOUND");
            }
            System.out.println(chatMessage.getSender().getDisplayName() + " usou !serverhost " + args[0]);
            return;
        }

        if(chatMessage.getContent().startsWith("!serverip")){
            String[] args = chatMessage.getContent().replace("!serverip", "").split(" ");
            if(args.length > 1){
                chatMessage.getSender().send("!serverip <minecraft domain ip>");
                return;
            }else{
                System.out.println(chatMessage.getSender().getDisplayName() + " usou !serverip " + args[0]);
                String get = get("http://vidalokapvp.tk/api/resolver.php?ip=" + args[0]);
                    if(get != null && get != args[0] && get != "ERROR") {
                        chatMessage.getSender().send("IP FOUND: " + get);
                        return;
                    }else{
                        String ip = args[0];
                        try {
                            InetAddress addr = InetAddress.getByName(ip);
                            ip = addr.getCanonicalHostName();
                            chatMessage.getSender().send("IP FOUND: " + ip + ":25565");
                            return;
                        } catch (UnknownHostException e2) {
                            System.out.println("Host desconhecido: " + ip);
                            chatMessage.getSender().send("IP NO FOUND!");
                            return;
                        }

                    }
            }
        }
    }

    @Override
    public void chatMessageSent(ChatMessage chatMessage) throws SkypeException {

    }

    public static String get(String url) {
        String stuff = "";
        try {
            URL site = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
            String str = in.readLine();
            in.close();
            if (str != null) {
                stuff = str;
            }
        }
        catch (java.io.IOException e1) {
            stuff = e1.getMessage();
        }
        return stuff;
    }
}
